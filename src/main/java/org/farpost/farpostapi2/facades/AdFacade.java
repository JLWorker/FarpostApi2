package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.farpost_ad_dto.UnpinFarpostAdDto;
import org.farpost.farpostapi2.dto.farpost_ad_dto.UpFarpostAdDto;
import org.farpost.farpostapi2.dto.farpost_ad_dto.FarpostAdResponseDto;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ViewDir;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.facades.utils.RequestVariables;
import org.farpost.farpostapi2.repositories.ViewDirsRepository;
import org.farpost.farpostapi2.services.RestService;
import org.farpost.farpostapi2.facades.utils.FarpostRequests;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AdFacade {

    private final FacadeUtils facadeUtils;
    private final RestService restService;

    @Transactional
    public FarpostAdResponseDto putUpAd(Integer clientId, UpFarpostAdDto upFarpostAdDto){
        Client client = facadeUtils.checkAvailability(clientId, Client.class, false);
        ViewDir viewDir = facadeUtils.checkAvailability(upFarpostAdDto.getViewDirId(), ViewDir.class, false);
        String boobs = String.format("boobs=%s", client.getBoobs());
        Map<String, Object> variables = Map.of(
                RequestVariables.AD_ID.getName(), upFarpostAdDto.getAdId(),
                RequestVariables.AD_VIEW_DIR.getName(), viewDir.getFarpostId(),
                RequestVariables.AD_PRICE.getName(), upFarpostAdDto.getPrice()
        );
        String res = restService.sendGetRequestSimple(FarpostRequests.UP_AD, variables , headers ->
            headers.add(HttpHeaders.COOKIE, boobs), String.class);
        System.out.println(res);
        return new FarpostAdResponseDto(upFarpostAdDto.getAdId(), "up");
    }

    @Transactional
    public FarpostAdResponseDto putUnpinAd(Integer clientId, UnpinFarpostAdDto unpinFarpostAdDto){
        Client client = facadeUtils.checkAvailability(clientId, Client.class, false);
        String boobs = String.format("boobs=%s", client.getBoobs());
        Map<String, Object> variables = Map.of(RequestVariables.AD_ID.getName(), unpinFarpostAdDto.getAdId());
        restService.sendGetRequestSimple(FarpostRequests.UNPIN_AD, variables , headers -> {
            headers.add(HttpHeaders.COOKIE, boobs);
        }, String.class);
        return new FarpostAdResponseDto(unpinFarpostAdDto.getAdId(), "unpin");
    }



}
