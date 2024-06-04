package org.farpost.farpostapi2.api;

import com.fasterxml.jackson.annotation.JsonView;
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
import org.farpost.farpostapi2.dto.ad_dto.AdDto;
import org.farpost.farpostapi2.dto.ad_dto.AdResponseDto;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.facades.AdFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
//@Tag(name = "api/auth/admin", description = "Admin controller api")
public class AdApi {

    private final AdFacade adFacade;

//    @Operation(summary = "Get all roles in system")
//    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer_uhdYUhskn879jd...")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "401", content = @Content(), headers = {
//                    @Header(name = "Expired", description = "Access token expired, need update", schema = @Schema(example = "true")),
//                    @Header(name = "Logout", description = "Refresh token not exist or expired, need logout", schema = @Schema(example = "true")),
//                    @Header(name = "Set-Cookie", description = "This header contains cookies to reset (with logout)", schema = @Schema(example = "REFRESH_TOKEN=; Max-Age=0; Expires=Thu, 01 Jan 1970 00:00:00 GMT"))
//            },description = "Refresh token not exist or expired"),
//            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(array = @ArraySchema(schema = @Schema(example = "ADMIN"))))
//    })
    @GetMapping("/up/{client_id}")
    public AdResponseDto putUpAd(@PathVariable(value = "client_id") Integer clientId, @Valid @RequestBody AdDto adDto){
       return adFacade.putUpAd(clientId, adDto);
    }

    @GetMapping("/unpin/{client_id}")
    public AdResponseDto putUnpinAd(@PathVariable(value = "client_id") Integer clientId, @Valid @RequestBody AdDto adDto){
        return adFacade.putUnpinAd(clientId, adDto);
    }


}
