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
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.admin_dto.AdminDto;
import org.farpost.farpostapi2.dto.farpost_ad_dto.FarpostAdResponseDto;
import org.farpost.farpostapi2.facades.AdminFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "admin", description = "Admin controller api")
public class AdminApi {

    private final AdminFacade adminFacade;

    @Operation(summary = "Get access token for swagger", description = "Return access token for swagger")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", content = @Content(), description = "Invalid user credentials"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(schema = @Schema(example = "asdjhagd34553sfhs24...")))
    })
    @PostMapping("/token")
    public String getAccessToken(@Valid @RequestBody AdminDto adminDto){
        return adminFacade.getAccessToken(adminDto);
    }

}
