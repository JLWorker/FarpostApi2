package org.farpost.farpostapi2.facades.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.User;
import org.farpost.farpostapi2.exceptions.system.ElementAlreadyExist;
import org.farpost.farpostapi2.exceptions.system.ElementNotFoundException;
import org.farpost.farpostapi2.repositories.ClientRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FacadeUtils {

   private final EntityManager entityManager;

   private final ClientRepository clientRepository;

   public <T> T checkAvailability(Integer elemId, Class<T> entityClass, boolean lockMode){
        T entity = !lockMode ? entityManager.find(entityClass, elemId) : entityManager.find(entityClass, elemId, LockModeType.PESSIMISTIC_WRITE);
        if (entity != null)
            return entity;
        else
            throw new ElementNotFoundException(elemId);
   }


    public String getUserBoobs(Integer clientId){
        Client client = clientRepository.getClientById(clientId);
        if (client!=null)
            return client.getBoobs();
        else
            throw new ElementNotFoundException(clientId);
    }
}

