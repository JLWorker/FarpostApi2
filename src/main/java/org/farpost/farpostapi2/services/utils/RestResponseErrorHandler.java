package org.farpost.farpostapi2.services.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.exceptions.farpost.BoobsInvalidException;
import org.farpost.farpostapi2.exceptions.system.RemoteInvalidRequestException;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class RestResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }

    @Override
    @SneakyThrows
    public void handleError(URI uri, HttpMethod method, ClientHttpResponse response) {
        String resp = new String(response.getBody().readAllBytes());
        log.info(resp.substring(0, 300));
        if (response.getStatusCode().value() == 401) {
            throw new BoobsInvalidException();
        }
        else if (response.getStatusCode().value() == 403) {
            return;
        }
        else
            throw new RemoteInvalidRequestException(String.format("Problem executing a remote request, message - %s", resp), uri.getPath(), response.getStatusCode().value(),
                    method.name());
    }

}
