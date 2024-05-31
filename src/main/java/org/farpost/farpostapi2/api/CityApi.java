package org.farpost.farpostapi2.api;

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
@Slf4j
public class CityApi {

    private final CityFacade cityFacade;

    @GetMapping
    public List<City> getAllCities(){;
        return cityFacade.getCities();
    }

    @PostMapping("/init")
    public void initAllCities(){;
         cityFacade.initCities();
    }

    @PostMapping("/add")
    @Transactional
    public City addNewCity(@Valid @RequestBody ViewDrCityDto viewDrCityDto){
        return cityFacade.addCity(viewDrCityDto);
    }

    @DeleteMapping("/{city_id}")
    public City deleteCity(@PathVariable("city_id") Integer cityId){
        return cityFacade.deleteCity(cityId);
    }

    @PutMapping("/{city_id}")
    public City updateCity(@PathVariable("city_id") Integer cityId, @Valid @RequestBody ViewDrCityDto viewDrCityDto){
        return cityFacade.updateCity(cityId, viewDrCityDto);
    }


}
