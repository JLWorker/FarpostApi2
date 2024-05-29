package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    @Query(value = "SELECT city FROM City city")
    List<City> getAllCities();

    @Query(value = "DELETE FROM cities WHERE id =:id RETURNING *", nativeQuery = true)
    City deleteCityById(@Param("id") Integer cityId);

    @Query(value = "UPDATE cities SET farpost_id = :farpostId, name = :name WHERE id = :id RETURNING *", nativeQuery = true)
    City updateCityById(@Param("farpostId") Integer farpostId, @Param("name") String name, @Param("id") Integer id);

}
