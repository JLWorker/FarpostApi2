package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.exceptions_dto.SimpleResponseExceptionDto;
import org.farpost.farpostapi2.dto.farpost_ad_dto.FarpostAdDto;
import org.farpost.farpostapi2.dto.farpost_ad_dto.FarpostAdResponseDto;
import org.farpost.farpostapi2.exceptions.security.SwaggerAccessExpiredException;
import org.farpost.farpostapi2.facades.AdFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
@Tag(name = "ad", description = "Ad controller api")
public class AdApi {

    private final AdFacade adFacade;

    @Operation(summary = "Put up farpost ad")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "client_id", in = ParameterIn.PATH, description = "Id client", example = "12")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", content = @Content(), description = "Swagger access token expired"),
            @ApiResponse(responseCode = "403", content = @Content(), description = "Swagger access token invalid"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(schema = @Schema(implementation = FarpostAdResponseDto.class)))
    })
    @PostMapping("/up/{client_id}")
    public FarpostAdResponseDto putUpAd(@PathVariable(value = "client_id") Integer clientId, @RequestBody @Valid FarpostAdDto farpostAdDto){
       return adFacade.putUpAd(clientId, farpostAdDto);
    }

    @Operation(summary = "Put unpin farpost ad")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "client_id", in = ParameterIn.PATH, description = "Id client", example = "12")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", content = @Content(), description = "Swagger access token expired"),
            @ApiResponse(responseCode = "403", content = @Content(), description = "Swagger access token invalid"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(schema = @Schema(implementation = FarpostAdResponseDto.class)))
    })
    @PostMapping("/unpin/{client_id}")
    public FarpostAdResponseDto putUnpinAd(@PathVariable(value = "client_id") Integer clientId, @RequestBody @Valid FarpostAdDto farpostAdDto){
        return adFacade.putUnpinAd(clientId, farpostAdDto);
    }


}
