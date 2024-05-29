package org.farpost.farpostapi2.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;
import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.facades.FacadeUtils;
import org.farpost.farpostapi2.repositories.CityRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
@Slf4j
public class CityApi {

    private final CityRepository cityRepository;
    private final FacadeUtils facadeUtils;

    @GetMapping
    public List<City> getAllCities(){;
        return cityRepository.getAllCities();
    }

    @PostMapping("/add")
    public City addNewCity(@Valid @RequestBody ViewDrCityDto viewDrCityDto){
        return cityRepository.save(new City(viewDrCityDto));
    }

    @DeleteMapping("/{city_id}")
    public City deleteCity(@PathVariable("city_id") Integer cityId){
        City city = cityRepository.deleteCityById(cityId);
        return facadeUtils.checkElemOnNull(city, cityId);
    }

    @PutMapping("/{city_id}")
    public City updateCity(@PathVariable("city_id") Integer cityId, @Valid @RequestBody ViewDrCityDto viewDrCityDto){
        City city = cityRepository.updateCityById(viewDrCityDto.getFarpostId(), viewDrCityDto.getName(), cityId);
        return facadeUtils.checkElemOnNull(city, cityId);
    }


}
