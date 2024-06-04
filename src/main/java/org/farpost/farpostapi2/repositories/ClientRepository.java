package org.farpost.farpostapi2.repositories;

import jakarta.persistence.LockModeType;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    @Query("SELECT client FROM Client client")
    List<Client> getAllClients();

    Client getClientById(Integer id);

    @Query("SELECT client from Client client JOIN ClientTg clientTg ON client.id=clientTg.client.id WHERE client.id = ?1")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Client> getClientWithTgById(Integer id);

    void deleteClientById(Integer id);

}
