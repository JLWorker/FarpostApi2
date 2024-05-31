package org.farpost.farpostapi2.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.client_dto.ClientDto;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.facades.ClientFacade;
import org.farpost.farpostapi2.repositories.ClientTgRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientApi {

    private final ClientFacade clientFacade;

    @PostMapping
    public Client addNewClient(@Valid @RequestBody ClientDto clientDto){
        return clientFacade.addNewClient(clientDto);
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientFacade.getClients();
    }

    @PutMapping("/{client_id}")
    public Client updateClient(@PathVariable(value = "client_id") Integer clientId, @Valid @RequestBody ClientDto clientDto){
        return clientFacade.updateClient(clientId, clientDto);
    }

    @DeleteMapping("/{client_id}")
    public Client deleteClient(@PathVariable(value = "client_id") Integer clientId){
        return clientFacade.deleteClient(clientId);
    }


}
