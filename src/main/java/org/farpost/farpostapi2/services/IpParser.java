package org.farpost.farpostapi2.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.farpost.farpostapi2.dto.timeweb_dto.TimewebIpResponseDto;
import org.farpost.farpostapi2.services.utils.ParserUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IpParser {

    private final ObjectMapper objectMapper;
    private final ParserUtils parserUtils;

    @SneakyThrows
    public TimewebIpResponseDto parseIpResponse(String response) {
        JsonNode tree = objectMapper.readTree(response.getBytes());
        JsonNode serverIp = tree.path("server_ip");
        parserUtils.checkPath(serverIp);
        return objectMapper.treeToValue(serverIp, TimewebIpResponseDto.class);
    }
}