package org.farpost.farpostapi2.api;

import com.fasterxml.jackson.annotation.JsonView;
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
import org.farpost.farpostapi2.dto.client_dto.ClientDto;
import org.farpost.farpostapi2.dto.client_dto.SuccessClientCreateDto;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.facades.ClientFacade;
import org.farpost.farpostapi2.repositories.ClientTgRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
@Tag(name = "client", description = "Client controller api")
public class ClientApi {

    private final ClientFacade clientFacade;

    @Operation(summary = "Add new client", description = "This endpoint start creation new VPS, you need wait 2 minutes after complete this request")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Vps not found"),
            @ApiResponse(responseCode = "400", content = @Content(), description = "Client or vps already exist"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(schema = @Schema(implementation = SuccessClientCreateDto.class)))
    })
    @PostMapping
    public SuccessClientCreateDto addNewClient(@Valid @RequestBody @JsonView(ClientDto.newClient.class) ClientDto clientDto){
        return clientFacade.addNewClient(clientDto);
    }

    @Operation(summary = "Get all clients")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(schema = @Schema(implementation = SuccessClientCreateDto.class)))
    })
    @GetMapping
    public List<Client> getAllClients(){
        return clientFacade.getClients();
    }

    @Operation(summary = "Update client")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "client_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Vps or client not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(schema = @Schema(implementation = SuccessClientCreateDto.class)))
    })

    @PutMapping("/{client_id}")
    public void updateClient(@PathVariable(value = "client_id") Integer clientId, @Valid @RequestBody @JsonView(ClientDto.updateClient.class) ClientDto clientDto){
         clientFacade.updateClient(clientId, clientDto);
    }


    @Operation(summary = "Delete client")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "client_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Client not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(schema = @Schema(implementation = SuccessClientCreateDto.class)))
    })
    @DeleteMapping("/{client_id}")
    public void deleteClient(@PathVariable(value = "client_id") Integer clientId){
         clientFacade.deleteClient(clientId);
    }


}
