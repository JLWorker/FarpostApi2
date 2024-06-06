package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.Ad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends CrudRepository<Ad, Integer> {



}
