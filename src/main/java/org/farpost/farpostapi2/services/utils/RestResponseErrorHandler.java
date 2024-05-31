package org.farpost.farpostapi2.services.utils;

import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.exceptions.RemoteInvalidRequestException;
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
        byte [] allBytes = response.getBody().readAllBytes();
        log.info(new String(allBytes, StandardCharsets.UTF_8));
        log.info(response.getStatusText() + response.getStatusCode().value());
       return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
    @Override
    public void handleError(URI uri, HttpMethod method, ClientHttpResponse response) throws IOException {
        log.error("Problem executing a remote request by uri {}", uri);
        throw new RemoteInvalidRequestException("Problem executing a remote request by uri", uri.getPath(), response.getStatusCode().value(),
                method.name());
    }

}
