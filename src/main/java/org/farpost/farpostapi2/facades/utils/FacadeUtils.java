package org.farpost.farpostapi2.facades.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.client_dto.ClientTelegramDto;
import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.farpost.farpostapi2.exceptions.ElementNotFoundException;
import org.farpost.farpostapi2.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FacadeUtils {

   private final EntityManager entityManager;
   private final ClientRepository clientRepository;

   public <T> T checkAvailability(Integer elemId, Class<T> entityClass){
        T entity = entityManager.find(entityClass, elemId, LockModeType.PESSIMISTIC_WRITE);
        if (entity != null)
            return entity;
        else
            throw  new ElementNotFoundException(String.format("Element with id - %s not found", elemId));
   }

    @Transactional(readOnly = true)
    public String getUserBoobs(Integer clientId){
        Client client = clientRepository.getClientById(clientId);
        if (client!=null)
            return client.getBoobs();
        else
            throw  new ElementNotFoundException(String.format("Element with id - %s not found", clientId));
    }
}

