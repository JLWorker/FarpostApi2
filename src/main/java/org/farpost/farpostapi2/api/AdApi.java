package org.farpost.farpostapi2.api;

import com.fasterxml.jackson.annotation.JsonView;
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
public class AdApi {

    private final AdFacade adFacade;

    @GetMapping("/up/{client_id}")
    public AdResponseDto putUpAd(@PathVariable(value = "client_id") Integer clientId, @Valid @RequestBody AdDto adDto){
       return adFacade.putUpAd(clientId, adDto);
    }

    @GetMapping("/unpin/{client_id}")
    public AdResponseDto putUnpinAd(@PathVariable(value = "client_id") Integer clientId, @Valid @RequestBody AdDto adDto){
        return adFacade.putUnpinAd(clientId, adDto);
    }


}
