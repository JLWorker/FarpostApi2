package org.farpost.farpostapi2.repositories;

import jakarta.persistence.LockModeType;
import org.farpost.farpostapi2.enitities.Bot;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BotRepository extends CrudRepository<Bot, Integer> {

    Optional<Bot> findByName(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT bot from  Bot bot LEFT JOIN Ad ad ON bot.id=ad.bot.id WHERE bot.id = ?1")
    Optional<Bot> findBotByIdAndLockAd(Integer id);

    @Query("select bot from Bot bot")
    List<Bot> getAllBots ();
}
