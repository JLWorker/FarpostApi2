package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.vps_dto.*;
import org.farpost.farpostapi2.enitities.Vps;
import org.farpost.farpostapi2.facades.VpsFacade;
import org.farpost.farpostapi2.facades.utils.TimewebRequests;
import org.farpost.farpostapi2.services.utils.FiledParseName;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vps")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
public class VpsApi {

    private final VpsFacade vpsFacade;

    @GetMapping("/os")
    public List<OsDto> getOs(){
         return vpsFacade.getAllOfTariffElements(TimewebRequests.GET_OS, FiledParseName.SERVER_OS, OsDto.class);
    }

    @GetMapping("/presets")
    public List<PresetDto> getPresets(){
        return vpsFacade.getAllOfTariffElements(TimewebRequests.GET_RESETS, FiledParseName.SERVER_PRESETS, PresetDto.class);
    }

    @GetMapping("/locations")
    public List<LocationDto> getLocations(){
        return vpsFacade.getAllOfTariffElements(TimewebRequests.GET_LOCATIONS, FiledParseName.LOCATIONS, LocationDto.class);
    }

    @PutMapping("/{vps_id}")
    public void updateVps(@PathVariable("vps_id") Integer vpsId, @RequestBody @Valid UpdateVpsDto updateVpsDto){
         vpsFacade.updateVps(vpsId, updateVpsDto);
    }

    @GetMapping()
    public List<VpsDto> getAllVps(){;
        return vpsFacade.getAllVps();
    }

    @DeleteMapping("/{vps_id}")
    public void deleteVps(@PathVariable("vps_id") Integer vpsId){
        vpsFacade.deleteVps(vpsId);
    }


}
