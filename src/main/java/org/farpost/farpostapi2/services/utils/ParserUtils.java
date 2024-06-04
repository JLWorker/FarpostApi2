package org.farpost.farpostapi2.services.utils;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.exceptions.system.NodeNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ParserUtils {

    public boolean checkPath(JsonNode jsonNode) throws Exception{
        if (jsonNode.isMissingNode()){
            Exception exception = new NodeNotFoundException(String.format("Node %s not found!", jsonNode.asText()));
            log.error(exception.getMessage());
            throw exception;
        }
        return true;
    }

}
