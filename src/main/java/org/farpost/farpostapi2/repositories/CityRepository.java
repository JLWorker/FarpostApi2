package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT city FROM City city")
    List<City> getAllCities();


    @Transactional
    @Query(value = "DELETE FROM cities WHERE id =?1 RETURNING *", nativeQuery = true)
    City deleteCityById(Integer cityId);

    @Transactional
    @Query(value = "UPDATE cities SET farpost_id = ?1, name = ?2 WHERE id = ?3 RETURNING *", nativeQuery = true)
    City updateCityById(Integer farpostId, String name, Integer id);

}
