package org.farpost.farpostapi2.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.farpost.farpostapi2.services.utils.FiledParseName;
import org.farpost.farpostapi2.services.utils.ParserUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VpsTariffsParser {

    private final ObjectMapper objectMapper;
    private final ParserUtils parserUtils;

    @SneakyThrows
    public <T> List<T> parseElemTariff(String response, Class<T> parserType, FiledParseName filedParseName){
        JsonNode tree = objectMapper.readTree(response.getBytes());
        JsonNode serversOs = tree.path(filedParseName.getValue());
        List<T> elemList = new ArrayList<>();
        if (parserUtils.checkPath(serversOs) && serversOs.isArray()){
            for (JsonNode node : serversOs){
                T elem = objectMapper.treeToValue(node, parserType);
                elemList.add(elem);
            };
        }
        return elemList;
    }

}
