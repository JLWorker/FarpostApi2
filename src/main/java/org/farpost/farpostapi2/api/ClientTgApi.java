package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.client_dto.ClientTgDto;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.farpost.farpostapi2.facades.ClientTgFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tg")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
public class ClientTgApi {

    private final ClientTgFacade clientTgFacade;

    @PostMapping("/{client_id}")
    public ClientTg addNewClientTg(@PathVariable(name = "client_id") Integer clientId, @Valid @RequestBody ClientTgDto clientTgDto){
        return clientTgFacade.createNewClientTg(clientId, clientTgDto);
    }

    @GetMapping()
    public List<ClientTg> getAllClientsTgs(){
        return clientTgFacade.getAllTgs();
    }

    @PutMapping("/{tg_id}")
    public void updateClientTg(@PathVariable(value = "tg_id") Integer tgtId, @Valid @RequestBody ClientTgDto clientTgDto){
        clientTgFacade.updateClientTg(tgtId, clientTgDto);
    }

    @DeleteMapping("/{tg_id}")
    public void deleteClientTg(@PathVariable(value = "tg_id") Integer tgId){
        clientTgFacade.deleteClientTg(tgId);
    }


}
