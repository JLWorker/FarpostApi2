package org.farpost.farpostapi2.services.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
        log.error(String.format("Problem executing a remote request by uri %s", uri.getRawPath()));
        String responseBody = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
        throw new RemoteInvalidRequestException(String.format("Problem executing a remote request, message - %s", responseBody), uri.getPath(), response.getStatusCode().value(),
                method.name());
    }

}
