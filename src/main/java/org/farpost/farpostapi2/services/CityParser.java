package org.farpost.farpostapi2.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.repositories.CityRepository;
import org.farpost.farpostapi2.services.utils.ParserUtils;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityParser {

    private final ObjectMapper objectMapper;
    private final ParserUtils parserUtils;
    private final CityRepository cityRepository;


    @SneakyThrows
    public void startParser(String path){
            JsonNode tree = objectMapper.readTree(Files.readAllBytes(Path.of(path)));
            JsonNode regions = tree.path("4").path("regions");
            if (parserUtils.checkPath(regions)) {
                regions.fields().forEachRemaining(fieldJsonNodeReg -> {
                    JsonNode cities = fieldJsonNodeReg.getValue().path("cities");
                    try {
                        if (parserUtils.checkPath(cities))
                            cities.fields().forEachRemaining(fieldJsonNodeCity -> {
                                Integer farpostId = Integer.valueOf(fieldJsonNodeCity.getKey());
                                String name = fieldJsonNodeCity.getValue().get("domain").asText();
                                cityRepository.save(new City(name, farpostId));
                            });
                        log.info("City parsing simplified successfully");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }

    }

}
