package org.farpost.farpostapi2.facades;

import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.exceptions.ElementNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FacadeUtils {

   public <T> T checkElemOnNull(T elem, Integer elemId){
        if (elem == null){
            throw new ElementNotFoundException(String.format("Element with id - %s not found", elemId));
        }
        else
            return elem;
    }

}
