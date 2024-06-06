package org.farpost.farpostapi2.services;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebVpsResponseDto;
import org.farpost.farpostapi2.services.utils.ParserUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VpsParser {

    private final ObjectMapper objectMapper;
    private final ParserUtils parserUtils;

    @SneakyThrows
    public TimewebVpsResponseDto parseVpsResponse(String response){
        TimewebVpsResponseDto timewebVpsResponseDto = new TimewebVpsResponseDto();
        JsonNode tree = objectMapper.readTree(response.getBytes());
        JsonNode idNode = tree.at(JsonPointer.compile("/server/id"));
        JsonNode rootPassword = tree.at(JsonPointer.compile("/server/root_pass"));
        JsonNode ipsNode = tree.at(JsonPointer.compile("/server/networks/0/ips"));
            if (parserUtils.checkPath(ipsNode) && parserUtils.checkPath(idNode) && (parserUtils.checkPath(rootPassword))){
                timewebVpsResponseDto.setVpsTimewebId(idNode.asInt());
                timewebVpsResponseDto.setRootPassword(rootPassword.asText());
                            for (JsonNode ipEl : ipsNode){
                                JsonNode ip = ipEl.path("ip");
                                JsonNode type = ipEl.path("type");
                                if (parserUtils.checkPath(ip) && parserUtils.checkPath(type)){
                                    if (type.asText().equals("ipv4")){
                                        timewebVpsResponseDto.setIpv4(ip.asText());
                                    }
                                    else
                                        timewebVpsResponseDto.setIpv6(ip.asText());
                                }
                            }
                        }
            return timewebVpsResponseDto;
        }
}

