package org.farpost.farpostapi2.services;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.farpost.farpostapi2.dto.timeweb_dto.CreateVpsResponseDto;
import org.farpost.farpostapi2.dto.vps_dto.VpsDto;
import org.farpost.farpostapi2.services.utils.ParserUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class VpsCreateParser {

    private final ObjectMapper objectMapper;
    private final ParserUtils parserUtils;

    @SneakyThrows
    public CreateVpsResponseDto parseVpsResponse(String response){
        CreateVpsResponseDto createVpsResponseDto = new CreateVpsResponseDto();
        JsonNode tree = objectMapper.readTree(response.getBytes());
        JsonNode idNode = tree.at(JsonPointer.compile("/server/id"));
        JsonNode ipsNode = tree.at(JsonPointer.compile("/server/networks/0/ips"));
            if (parserUtils.checkPath(ipsNode) && parserUtils.checkPath(idNode)){
                createVpsResponseDto.setVpsTimewebId(idNode.asInt());
                            for (JsonNode ipEl : ipsNode){
                                JsonNode ip = ipEl.path("ip");
                                JsonNode type = ipEl.path("type");
                                if (parserUtils.checkPath(ip) && parserUtils.checkPath(type)){
                                    if (type.asText().equals("ipv4")){
                                        createVpsResponseDto.setIpv4(ip.asText());
                                    }
                                    else
                                        createVpsResponseDto.setIpv6(ip.asText());
                                }
                            }
                        }
            return createVpsResponseDto;
        }
}

