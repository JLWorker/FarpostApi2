package org.farpost.farpostapi2.repositories;

import jakarta.persistence.LockModeType;
import org.farpost.farpostapi2.dto.client_dto.ClientTgDto;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientTgRepository extends CrudRepository<ClientTg, Integer> {

    @Query("SELECT clientTg FROM ClientTg clientTg")
    List<ClientTg> getAllClientTg();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ClientTg> findClientTgById(Integer id);
}
