package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.vps_dto.VpsDto;
import org.farpost.farpostapi2.enitities.Vps;
import org.farpost.farpostapi2.exceptions.system.ElementNotFoundException;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.repositories.VpsRepository;
import org.farpost.farpostapi2.services.VpsTariffsParser;
import org.farpost.farpostapi2.services.RestService;
import org.farpost.farpostapi2.services.utils.FiledParseName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VpsFacade {

    @Value("${timeweb.access_token}")
    private String timewebAccess;

    private final RestService restService;
    private final VpsTariffsParser vpsTariffsParser;
    private final FacadeUtils facadeUtils;
    private final VpsRepository vpsRepository;

    public <T> List<T> getAllOfTariffElements(String requests, FiledParseName parseName, Class<T> parseType){
        String response = restService.sendGetRequestSimple(requests, null, headers -> headers.add("Authorization", timewebAccess), String.class);
        return vpsTariffsParser.parseElemTariff(response, parseType, parseName);
    }

    @Transactional(readOnly = true)
    public void updateVps(Integer vpsId, VpsDto vpsDto){
        Vps vps = facadeUtils.checkAvailability(vpsId, Vps.class, true);
        vps.setIp(vpsDto.getIp());
        vps.setRing(vpsDto.getRing());
        vps.setTimewebId(vpsDto.getTimewebId());
        vps.setName(vpsDto.getName());
        vpsRepository.save(vps);
    }

    @Transactional
    public void deleteVps(Integer vpsId){
        Optional<Vps> vps = vpsRepository.getVpsCascadeLockById(vpsId);
        if (vps.isPresent()){
            deleteVps(vpsId);
        }
        else
            throw new ElementNotFoundException(vpsId);
    }

}
