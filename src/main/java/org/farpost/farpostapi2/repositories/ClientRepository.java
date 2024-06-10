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

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT client from Client client LEFT JOIN client.clientTgs clientTgs WHERE client.id = ?1")
    Optional<Client> getClientWithTgById(Integer id);

    Optional<Client> findClientByBoobs(String boobs);

    void deleteClientById(Integer id);

}
