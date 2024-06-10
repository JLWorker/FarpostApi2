package org.farpost.farpostapi2.services;

import com.sun.net.httpserver.Headers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.farpost.farpostapi2.services.utils.RestResponseErrorHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestService {

private final RestClient restClient = RestClient.builder().requestFactory(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault())).build();
    private final RestResponseErrorHandler restResponseErrorHandler;

    public <T> T sendGetRequestSimple(String requestUri, Map<String, Object> variables, Consumer<HttpHeaders> headersConsumer, Class<T> responseType){
        URI buildRequest = getHttpUri(variables, requestUri);
        return restClient.get()
                .uri(buildRequest)
                .headers(headersConsumer)
                .retrieve()
                .onStatus(restResponseErrorHandler)
                .body(responseType);
    }

    public void sendGetRequestSimpleWithoutAnswer(String requestUri, Map<String, Object> variables, Consumer<HttpHeaders> headersConsumer){
        URI buildRequest = getHttpUri(variables, requestUri);
         restClient.get()
                .uri(buildRequest)
                .headers(headersConsumer)
                .retrieve()
                .onStatus(restResponseErrorHandler)
                .toBodilessEntity();
    }

    public <T, R> T sendPostRequestSimple(String requestUri, R requestBody,  Map<String, Object> variables, Consumer<HttpHeaders> headersConsumer, Class<T> responseType){
        URI buildRequest = getHttpUri(variables, requestUri);
        return restClient.post()
                .uri(buildRequest)
                .headers(headersConsumer)
                .body(requestBody)
                .retrieve()
                .onStatus(restResponseErrorHandler)
                .body(responseType);
    }

    public void sendDeleteRequestSimple(String requestUri, Map<String, Object> variables, Consumer<HttpHeaders> headersConsumer){
        URI buildRequest = getHttpUri(variables, requestUri);
        restClient.delete()
                .uri(buildRequest)
                .headers(headersConsumer)
                .retrieve()
                .onStatus(restResponseErrorHandler)
                .toBodilessEntity();
    }

    private URI getHttpUri(Map<String, Object> variables, String requestUri){
        if (variables != null)
            return UriComponentsBuilder.fromHttpUrl(requestUri).buildAndExpand(variables).toUri();
        else
            return URI.create(requestUri);
    }

}
