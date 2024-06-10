package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.Ad;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends CrudRepository<Ad, Integer> {

    @Modifying
    @Query("delete from Ad ad where ad.bot.id = ?1")
    void deleteAllByBotId(Integer botId);

}
