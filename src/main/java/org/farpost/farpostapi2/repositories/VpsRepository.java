package org.farpost.farpostapi2.repositories;

import jakarta.persistence.LockModeType;
import org.farpost.farpostapi2.enitities.Vps;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VpsRepository extends CrudRepository<Vps, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT vps from Vps vps JOIN Client client ON client.vps.id = vps.id WHERE vps.id = ?1")
    Optional<Vps> getVpsCascadeLockById(Integer id);

    Optional<Vps> findByName(String name);

    @Query("SELECT vps FROM Vps vps")
    List<Vps> getAllVps();

}
