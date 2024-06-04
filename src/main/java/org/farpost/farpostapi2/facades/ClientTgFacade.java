package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.client_dto.ClientTgDto;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.farpost.farpostapi2.exceptions.system.ElementNotFoundException;
import org.farpost.farpostapi2.repositories.ClientRepository;
import org.farpost.farpostapi2.repositories.ClientTgRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientTgFacade {

    private final ClientTgRepository clientTgRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public ClientTg createNewClientTg(Integer clientId, ClientTgDto clientTgDto){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ElementNotFoundException(clientId));
        return clientTgRepository.save(new ClientTg(clientTgDto, client));
    }

    @Transactional(readOnly = true)
    public List<ClientTg> getAllTgs(){
        return clientTgRepository.getAllClientTg();
    }

    @Transactional
    public void updateClientTg(Integer tgId, ClientTgDto clientTgDto){
        ClientTg clientTg = clientTgRepository.findClientTgById(tgId)
                .orElseThrow(() -> new ElementNotFoundException(tgId));
        clientTg.setUsername(clientTgDto.getUsername());
        clientTg.setTgId(clientTgDto.getTgId());
        clientTgRepository.save(clientTg);

    }

    @Transactional
    public void deleteClientTg(Integer tgId){
        clientTgRepository.findClientTgById(tgId)
                .orElseThrow(() -> new ElementNotFoundException(tgId));
        clientTgRepository.deleteById(tgId);
    }
}
