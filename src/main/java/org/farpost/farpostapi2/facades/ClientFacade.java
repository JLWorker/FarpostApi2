package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.client_dto.ClientDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebIpDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebIpResponseDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebVpsDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebVpsResponseDto;
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
import org.farpost.farpostapi2.services.IpParser;
import org.farpost.farpostapi2.services.RestService;
import org.farpost.farpostapi2.services.VpsParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientFacade {

    private final ClientRepository clientRepository;
    private final FacadeUtils facadeUtils;
    private final ClientTgRepository clientTgRepository;
    private final RestService restService;
    private final VpsParser vpsParser;
    private final VpsRepository vpsRepository;
    private final IpParser ipParser;

    @Value("${timeweb.access_token}")
    private String timewebAccess;

    @Transactional
    public void addNewClient(ClientDto clientDto) {
        if(!checkClientOnExistBoobs(clientDto.getBoobs())) {
            Client client = new Client(clientDto);
            if (clientDto.getVpsId() != null) {
                Vps vps = facadeUtils.checkAvailability(clientDto.getVpsId(), Vps.class, false);
                client.setVps(vps);
            } else if (clientDto.getClientVpsDto() != null) {
                if (!checkOnExistVps(clientDto.getClientVpsDto().getVpsName())) {
                    TimewebVpsDto timewebVpsDto = new TimewebVpsDto(clientDto.getClientVpsDto());
                    String response = restService.sendPostRequestSimple(TimewebRequests.CREATE_VPS, timewebVpsDto, null, headers ->
                            headers.add("Authorization", timewebAccess), String.class);

                    TimewebVpsResponseDto timewebVpsResponseDto = vpsParser.parseVpsResponse(response);
                    Map<String, Object> variables = Map.of("timeweb_id", timewebVpsResponseDto.getVpsTimewebId());
                    String ipv4Response = restService.sendPostRequestSimple(TimewebRequests.ADD_IPV4, new TimewebIpDto("ipv4"), variables, headers ->
                            headers.add("Authorization", timewebAccess), String.class);

                    TimewebIpResponseDto timewebIpResponseDto = ipParser.parseIpResponse(ipv4Response);
                    if (timewebIpResponseDto.getType().equals("ipv4"))
                        timewebVpsResponseDto.setIpv4(timewebIpResponseDto.getIp());
                    Vps vps = vpsRepository.save(new Vps(clientDto.getClientVpsDto().getVpsName(), timewebVpsResponseDto));
                    client.setVps(vps);
                    clientRepository.save(client);
                }
            } else
                throw new BadRequestException("Without specifying the vps ID, you must pass data to create a vps, also parameter imageId should be without osId");
        }
    }

    private boolean checkOnExistVps(String name){
         if(vpsRepository.findByName(name).isPresent())
             throw new ElementAlreadyExist(String.format("Vps with name %s already exist", name));
         else
             return false;
    }

    private boolean checkClientOnExistBoobs(String boobs){
        if(clientRepository.findClientByBoobs(boobs).isPresent())
            throw new ElementAlreadyExist(String.format("Client with boobs %s already exist", boobs));
        else
            return false;
    }

    //желательно поправить параллелизм
    @Async
    public void saveCreatedVps(ClientDto clientDto, Client client, String response){
            TimewebVpsResponseDto timewebVpsResponseDto = vpsParser.parseVpsResponse(response);
            Vps vps = vpsRepository.save(new Vps(clientDto.getClientVpsDto().getVpsName(), timewebVpsResponseDto));
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
        clientRepository.save(client);
//        if(!clientDto.getListTgId().isEmpty()){
//            Map<Integer, ClientTg> clientTgMap = client.getClientTgs()
//                    .stream().collect(Collectors.toMap(ClientTg::getTgId, value -> value));
//            clientDto.getListTgId().forEach(el -> {
//                ClientTg clientTg = new ClientTg(el);
//                if (clientTgMap.containsKey(clientTg.getTgId())){
//                    ClientTg findPresentClient = clientTgMap.get(clientTg.getTgId());
//                    if (!findPresentClient.equals(clientTg)) {
//                        findPresentClient.setUsername(clientTg.getUsername());
//                        findPresentClient.setTgId(clientTg.getTgId());
//                        clientTgRepository.save(clientTg);
//                    }
//                }
//                else {
//                    clientTg.setClient(client);
//                    clientTgRepository.save(clientTg);
//                }
//            });
//        }
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
