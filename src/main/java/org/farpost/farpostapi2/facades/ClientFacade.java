package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.client_dto.ClientDto;
import org.farpost.farpostapi2.dto.timeweb_dto.CreateVpsDto;
import org.farpost.farpostapi2.dto.timeweb_dto.CreateVpsResponseDto;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.farpost.farpostapi2.enitities.Vps;
import org.farpost.farpostapi2.exceptions.system.BadRequestException;
import org.farpost.farpostapi2.exceptions.system.ElementAlreadyExist;
import org.farpost.farpostapi2.exceptions.system.ElementNotFoundException;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.facades.utils.TimewebRequests;
import org.farpost.farpostapi2.repositories.ClientRepository;
import org.farpost.farpostapi2.repositories.ClientTgRepository;
import org.farpost.farpostapi2.repositories.VpsRepository;
import org.farpost.farpostapi2.services.RestService;
import org.farpost.farpostapi2.services.VpsCreateParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientFacade {

    private final ClientRepository clientRepository;
    private final FacadeUtils facadeUtils;
    private final ClientTgRepository clientTgRepository;
    private final RestService restService;
    private final VpsCreateParser vpsCreateParser;
    private final VpsRepository vpsRepository;

    @Value("${timeweb.access_token}")
    private String timewebAccess;

    @Transactional
    public void addNewClient(ClientDto clientDto){
        Client client = new Client(clientDto);
        if (clientDto.getVpsId() != null) {
            Vps vps = facadeUtils.checkAvailability(clientDto.getVpsId(), Vps.class, false);
            client.setVps(vps);
        }
        else
            if (clientDto.getClientVpsDto() != null){
                if (!checkOnExistVps(clientDto.getClientVpsDto().getVpsName())) {
                    CreateVpsDto createVpsDto = new CreateVpsDto(clientDto.getClientVpsDto());
                    String response = restService.sendPostRequestSimple(TimewebRequests.CREATE_VPS, createVpsDto, null, headers ->
                            headers.add("Authorization", timewebAccess), String.class);
                    saveCreatedVps(clientDto, client, response);
                }
            }
            else
                throw new BadRequestException("Without specifying the vps ID, you must pass data to create a vps.");
    }

    private boolean checkOnExistVps(String name){
         if(vpsRepository.findByName(name).isPresent())
             throw new ElementAlreadyExist(String.format("Vps with name %s already exist", name));
         else
             return false;
    }

    //желательно поправить параллелизм
    @Async
    public void saveCreatedVps(ClientDto clientDto, Client client, String response){
            CreateVpsResponseDto createVpsResponseDto = vpsCreateParser.parseVpsResponse(response);
            Vps vps = vpsRepository.save(new Vps(clientDto.getClientVpsDto().getVpsName(), createVpsResponseDto));
            client.setVps(vps);
            clientRepository.save(client);
    }


    @Transactional(readOnly = true)
    public List<Client> getClients(){
        return clientRepository.getAllClients();
    }

    @Transactional
    public void updateClient(Integer clientId, ClientDto clientDto){
        Client client = getBlockForClientCascade(clientId);
        client.setName(clientDto.getName());
        client.setBoobs(clientDto.getBoobs());
        if (clientDto.getVpsId() != null) {
            Vps vps = facadeUtils.checkAvailability(clientDto.getVpsId(), Vps.class, false);
            client.setVps(vps);
        }
        if(!clientDto.getListTgId().isEmpty()){
            Map<Integer, ClientTg> clientTgMap = client.getClientTgs()
                    .stream().collect(Collectors.toMap(ClientTg::getTgId, value -> value));
            clientDto.getListTgId().forEach(el -> {
                ClientTg clientTg = new ClientTg(el);
                if (clientTgMap.containsKey(clientTg.getTgId())){
                    ClientTg findPresentClient = clientTgMap.get(clientTg.getTgId());
                    if (!findPresentClient.equals(clientTg)) {
                        findPresentClient.setUsername(clientTg.getUsername());
                        findPresentClient.setTgId(clientTg.getTgId());
                        clientTgRepository.save(clientTg);
                    }
                }
                else {
                    clientTg.setClient(client);
                    clientTgRepository.save(clientTg);
                }
            });
        }
    }

    @Transactional
    public void deleteClient(Integer clientId){
            getBlockForClientCascade(clientId);
            clientRepository.deleteClientById(clientId);
    }

    private Client getBlockForClientCascade(Integer clientId){
        return clientRepository.getClientWithTgById(clientId).orElseThrow(() ->
                new ElementNotFoundException(clientId)
        );
    }

}
