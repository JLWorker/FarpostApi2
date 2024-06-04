package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.ad_dto.AdDto;
import org.farpost.farpostapi2.dto.ad_dto.AdResponseDto;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.facades.utils.RequestVariables;
import org.farpost.farpostapi2.services.RestService;
import org.farpost.farpostapi2.facades.utils.FarpostRequests;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AdFacade {

    private final FacadeUtils facadeUtils;
    private final RestService restService;

    public AdResponseDto putUpAd(Integer clientId, AdDto adDto){
        String boobs = String.format("boobs=%s", facadeUtils.getUserBoobs(clientId));
        Map<String, Object> variables = Map.of(
                RequestVariables.AD_ID.getName(), adDto.getAdId(),
                RequestVariables.AD_VIEW_DIR.getName(), adDto.getViewDirId(),
                RequestVariables.AD_PRICE.getName(), adDto.getPrice()
        );
        restService.sendGetRequestSimple(FarpostRequests.UP_AD, variables , headers -> {
            headers.add(HttpHeaders.COOKIE, boobs);
            headers.add(HttpHeaders.CONTENT_TYPE, "text/html; charset=windows-1251");
        }, String.class);
        return new AdResponseDto(adDto.getAdId(), "up");
    }

    public AdResponseDto putUnpinAd(Integer clientId, AdDto adDto){
        String boobs = String.format("boobs=%s", facadeUtils.getUserBoobs(clientId));
        Map<String, Object> variables = Map.of(RequestVariables.AD_ID.getName(), adDto.getAdId());
        restService.sendGetRequestSimple(FarpostRequests.UNPIN_AD, variables , headers -> {
            headers.add(HttpHeaders.COOKIE, boobs);
            headers.add(HttpHeaders.CONTENT_TYPE, "text/html; charset=windows-1251");
        }, String.class);
        return new AdResponseDto(adDto.getAdId(), "unpin");
    }



}
