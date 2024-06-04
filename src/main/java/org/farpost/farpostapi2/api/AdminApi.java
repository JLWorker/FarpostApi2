package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.admin_dto.AdminDto;
import org.farpost.farpostapi2.facades.AdminFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApi {

    private final AdminFacade adminFacade;

    @PostMapping("/token")
    public String getAccessToken(@Valid @RequestBody AdminDto adminDto){
        return adminFacade.getAccessToken(adminDto);
    }

}
