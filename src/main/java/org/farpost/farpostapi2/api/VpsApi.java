package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.vps_dto.LocationDto;
import org.farpost.farpostapi2.dto.vps_dto.OsDto;
import org.farpost.farpostapi2.dto.vps_dto.PresetDto;
import org.farpost.farpostapi2.dto.vps_dto.VpsDto;
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
    public void updateVps(@PathVariable("vps_id") Integer vpsId, @RequestBody @Valid VpsDto vpsDto){
         vpsFacade.updateVps(vpsId, vpsDto);
    }

    @DeleteMapping("/{vps_id}")
    public void deleteVps(@PathVariable("vps_id") Integer vpsId){
        vpsFacade.deleteVps(vpsId);
    }

//    @GetMapping
//    public List<Vps> getAllVps(){;
//        return vpsFacade.getVps();
//    }
//
//    @PostMapping()
//    public Vps addNewVps(@Valid @RequestBody ViewDrCityDto viewDrCityDto){
//        return cityFacade.addCity(viewDrCityDto);
//    }
//
//    @DeleteMapping("/{city_id}")
//    public void deleteCity(@PathVariable("city_id") Integer cityId){
//        cityFacade.deleteCity(cityId);
//    }
//
//    @PutMapping("/{city_id}")
//    public void updateCity(@PathVariable("city_id") Integer cityId, @Valid @RequestBody ViewDrCityDto viewDrCityDto){
//        cityFacade.updateCity(cityId, viewDrCityDto);
//    }

}
