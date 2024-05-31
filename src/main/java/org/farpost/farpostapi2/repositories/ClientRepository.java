package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    @Query("SELECT client FROM Client client")
    List<Client> getAllClients();

    Client getClientById(Integer id);

    void deleteClientById(Integer id);

}
