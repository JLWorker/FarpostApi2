package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.farpost_ad_dto.FarpostAdDto;
import org.farpost.farpostapi2.dto.farpost_ad_dto.FarpostAdResponseDto;
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
    public FarpostAdResponseDto putUpAd(@PathVariable(value = "client_id") Integer clientId, @Valid @RequestBody FarpostAdDto farpostAdDto){
       return adFacade.putUpAd(clientId, farpostAdDto);
    }

    @GetMapping("/unpin/{client_id}")
    public FarpostAdResponseDto putUnpinAd(@PathVariable(value = "client_id") Integer clientId, @Valid @RequestBody FarpostAdDto farpostAdDto){
        return adFacade.putUnpinAd(clientId, farpostAdDto);
    }


}
