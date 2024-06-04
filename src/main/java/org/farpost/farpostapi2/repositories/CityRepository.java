package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    @Query(value = "SELECT city FROM City city")
    List<City> getAllCities();

    @Query(value = "DELETE FROM cities WHERE id =?1 RETURNING *", nativeQuery = true)
    City deleteCityById(Integer cityId);

}
