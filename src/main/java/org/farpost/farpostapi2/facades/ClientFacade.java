package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.client_dto.ClientDto;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.farpost.farpostapi2.enitities.Vps;
import org.farpost.farpostapi2.exceptions.system.ElementNotFoundException;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.repositories.ClientRepository;
import org.farpost.farpostapi2.repositories.ClientTgRepository;
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

    @Transactional
    public Client addNewClient(ClientDto clientDto){
        Client client = new Client(clientDto);
        if (clientDto.getVpsId() != null) {
            Vps vps = facadeUtils.checkAvailability(clientDto.getVpsId(), Vps.class, false);
            client.setVps(vps);
        }
        return clientRepository.save(client);
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
