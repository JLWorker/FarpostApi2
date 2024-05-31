package org.farpost.farpostapi2.repositories;

import jakarta.persistence.LockModeType;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientTgRepository extends CrudRepository<ClientTg, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    ClientTg findClientTgByTgId(Integer tgId);

    @Query("SELECT clientTg FROM ClientTg clientTg WHERE clientTg.client.id = ?1")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ClientTg> getClientTgsByClientId(Integer clientId);
}
