package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebImagesDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebLocationDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebOsDto;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebPresetDto;
import org.farpost.farpostapi2.dto.vps_dto.*;
import org.farpost.farpostapi2.facades.VpsFacade;
import org.farpost.farpostapi2.facades.utils.TimewebRequests;
import org.farpost.farpostapi2.services.utils.FiledParseName;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vps")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
@Tag(name = "vps", description = "Vps controller api")
public class VpsApi {

    private final VpsFacade vpsFacade;

    @Operation(summary = "Get timeweb os")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", content = @Content(), description = "Parser exception or rest exception"),
            @ApiResponse(responseCode = "200", description = "Success operation")
    })
    @GetMapping("/os")
    public List<TimewebOsDto> getOs(){
         return vpsFacade.getAllOfTariffElements(TimewebRequests.GET_OS, FiledParseName.SERVER_OS, TimewebOsDto.class);
    }

    @Operation(summary = "Get timeweb presets")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", content = @Content(), description = "Parser exception or rest exception"),
            @ApiResponse(responseCode = "200", description = "Success operation")
    })
    @GetMapping("/presets")
    public List<TimewebPresetDto> getPresets(){
        return vpsFacade.getAllOfTariffElements(TimewebRequests.GET_RESETS, FiledParseName.SERVER_PRESETS, TimewebPresetDto.class);
    }

    @Operation(summary = "Get timeweb locations")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", content = @Content(), description = "Parser exception or rest exception"),
            @ApiResponse(responseCode = "200", description = "Success operation")
    })
    @GetMapping("/locations")
    public List<TimewebLocationDto> getLocations(){
        return vpsFacade.getAllOfTariffElements(TimewebRequests.GET_LOCATIONS, FiledParseName.LOCATIONS, TimewebLocationDto.class);
    }

    @Operation(summary = "Get timeweb images")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", content = @Content(), description = "Parser exception or rest exception"),
            @ApiResponse(responseCode = "200", description = "Success operation")
    })
    @GetMapping("/images")
    public List<TimewebImagesDto> getImages(){
        return vpsFacade.getAllOfTariffElements(TimewebRequests.GET_IMAGES, FiledParseName.IMAGES, TimewebImagesDto.class);
    }

    @Operation(summary = "Update vps")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "vps_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Vps not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @PutMapping("/{vps_id}")
    public void updateVps(@PathVariable("vps_id") Integer vpsId, @RequestBody @Valid UpdateVpsDto updateVpsDto){
         vpsFacade.updateVps(vpsId, updateVpsDto);
    }

    @Operation(summary = "Get all vps")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation")
    })
    @GetMapping()
    public List<VpsDto> getAllVps(){;
        return vpsFacade.getAllVps();
    }

    @Operation(summary = "Delete vps")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "vps_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "Vps not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @DeleteMapping("/{vps_id}")
    public void deleteVps(@PathVariable("vps_id") Integer vpsId){
        vpsFacade.deleteVps(vpsId);
    }


}
