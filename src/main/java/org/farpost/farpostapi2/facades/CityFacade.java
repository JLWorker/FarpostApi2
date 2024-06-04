package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;
import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.repositories.CityRepository;
import org.farpost.farpostapi2.services.CityParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CityFacade {

    private final CityRepository cityRepository;
    private final CityParser cityParser;
    private final FacadeUtils facadeUtils;

    @Value("${parsers.city_path}")
    private String cityPath;

    @Transactional(readOnly = true)
    public List<City> getCities(){;
        return cityRepository.getAllCities();
    }

    @Transactional
    public void initCities(){;
        cityParser.startParser(cityPath);
    }

    @Transactional
    public City addCity(ViewDrCityDto viewDrCityDto){
        return cityRepository.save(new City(viewDrCityDto));
    }

    @Transactional
    public void deleteCity(Integer cityId){
        facadeUtils.checkAvailability(cityId, City.class, true);
        cityRepository.deleteCityById(cityId);
    }

    @Transactional
    public void updateCity(Integer cityId, ViewDrCityDto viewDrCityDto){
        City city = facadeUtils.checkAvailability(cityId, City.class, true);
        city.setName(viewDrCityDto.getName());
        city.setFarpostId(viewDrCityDto.getFarpostId());
        cityRepository.save(city);
    }


}
