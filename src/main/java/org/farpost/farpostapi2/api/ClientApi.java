package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "Authentication")
public class ClientApi {

    private final ClientFacade clientFacade;

    @PostMapping
    public void addNewClient(@Valid @RequestBody ClientDto clientDto){
        clientFacade.addNewClient(clientDto);
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientFacade.getClients();
    }

    @PutMapping("/{client_id}")
    public void updateClient(@PathVariable(value = "client_id") Integer clientId, @Valid @RequestBody ClientDto clientDto){
         clientFacade.updateClient(clientId, clientDto);
    }

    @DeleteMapping("/{client_id}")
    public void deleteClient(@PathVariable(value = "client_id") Integer clientId){
         clientFacade.deleteClient(clientId);
    }


}
