package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.Bot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BotRepository extends CrudRepository<Bot, Integer> {

    Optional<Bot> findByName(String name);

}
