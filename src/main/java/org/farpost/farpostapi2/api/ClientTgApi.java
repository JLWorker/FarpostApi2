package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.client_dto.ClientTgDto;
import org.farpost.farpostapi2.dto.client_dto.SuccessClientCreateDto;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.farpost.farpostapi2.facades.ClientTgFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tg")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
@Tag(name = "tg", description = "Client tg controller api")
public class ClientTgApi {

    private final ClientTgFacade clientTgFacade;


    @Operation(summary = "Create new client tg")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "client_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Client not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @PostMapping("/{client_id}")
    public void addNewClientTg(@PathVariable(name = "client_id") Integer clientId, @Valid @RequestBody ClientTgDto clientTgDto){
        clientTgFacade.createNewClientTg(clientId, clientTgDto);
    }

    @Operation(summary = "Get all client tg")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "tg_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation")
    })
    @GetMapping()
    public List<ClientTg> getAllClientsTgs(){
        return clientTgFacade.getAllTgs();
    }

    @Operation(summary = "Update client tg")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "tg_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Client tg not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @PutMapping("/{tg_id}")
    public void updateClientTg(@PathVariable(value = "tg_id") Integer tgtId, @Valid @RequestBody ClientTgDto clientTgDto){
        clientTgFacade.updateClientTg(tgtId, clientTgDto);
    }

    @Operation(summary = "Delete client tg")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "tg_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Client tg not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @DeleteMapping("/{tg_id}")
    public void deleteClientTg(@PathVariable(value = "tg_id") Integer tgId){
        clientTgFacade.deleteClientTg(tgId);
    }


}
