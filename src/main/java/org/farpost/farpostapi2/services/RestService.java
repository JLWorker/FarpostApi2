package org.farpost.farpostapi2.services;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.ad_dto.AdDto;
import org.farpost.farpostapi2.services.utils.RestResponseErrorHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class RestService {

    private final RestClient restClient = RestClient.create();
    private final RestResponseErrorHandler restResponseErrorHandler;

    public void sendGetRequest(String requestUri, Map<String, Object> variables, Consumer<HttpHeaders> headersConsumer){
         URI uri = UriComponentsBuilder.fromHttpUrl(requestUri).buildAndExpand(variables).toUri();
         restClient.get()
                .uri(uri)
                .headers(headersConsumer)
                .retrieve()
                .onStatus(restResponseErrorHandler);
    }


}
