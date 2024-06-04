package org.farpost.farpostapi2.repositories;

import jakarta.persistence.LockModeType;
import org.farpost.farpostapi2.enitities.Vps;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VpsRepository extends CrudRepository<Vps, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT vps from Vps vps JOIN Client client ON client.vps.id = vps.id JOIN ClientTg clientTg ON client.id = clientTg.client.id" +
            " JOIN Bot bot ON bot.vps.id = bot.id WHERE client.id = ?1")
    Optional<Vps> getVpsCascadeLockById(Integer id);

}
