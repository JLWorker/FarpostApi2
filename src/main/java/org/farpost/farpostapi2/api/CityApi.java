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
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;
import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.facades.CityFacade;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
@Tag(name = "city", description = "City controller api")
@Slf4j
public class CityApi {

    private final CityFacade cityFacade;


    @Operation(summary = "Get all cities")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation")
    })
    @GetMapping
    public List<City> getAllCities(){;
        return cityFacade.getCities();
    }

    @Operation(summary = "Init cities from json", description = "Start parser for city")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", content = @Content(), description = "Problem with parser"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @PostMapping("/init")
    public void initAllCities(){;
         cityFacade.initCities();
    }

    @Operation(summary = "Add new city")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", content = @Content(), description = "City already exist or validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @PostMapping()
    public void addNewCity(@Valid @RequestBody ViewDrCityDto viewDrCityDto){
         cityFacade.addCity(viewDrCityDto);
    }

    @Operation(summary = "Delete city")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "city_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "City not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @DeleteMapping("/{city_id}")
    public void deleteCity(@PathVariable("city_id") Integer cityId){
        cityFacade.deleteCity(cityId);
    }

    @Operation(summary = "Update city")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "city_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "City not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @PutMapping("/{city_id}")
    public void updateCity(@PathVariable("city_id") Integer cityId, @Valid @RequestBody ViewDrCityDto viewDrCityDto){
        cityFacade.updateCity(cityId, viewDrCityDto);
    }


}
