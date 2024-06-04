package org.farpost.farpostapi2.services;

import com.sun.net.httpserver.Headers;
import lombok.RequiredArgsConstructor;
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

//    public void sendGetRequestWithoutAnswer(String requestUri, Map<String, Object> variables, Consumer<HttpHeaders> headersConsumer){
//        URI uri = UriComponentsBuilder.fromHttpUrl(requestUri).buildAndExpand(variables).toUri();
//        restClient.get()
//                .uri(uri)
//                .headers(headersConsumer)
//                .retrieve()
//                .onStatus(restResponseErrorHandler);
//    }

    public <T> T sendGetRequestSimple(String requestUri, Map<String, Object> variables, Consumer<HttpHeaders> headersConsumer, Class<T> responseType){
        URI uri;
        if (variables != null)
            uri = UriComponentsBuilder.fromHttpUrl(requestUri).buildAndExpand(variables).toUri();
        else
            uri = URI.create(requestUri);
        return restClient.get()
                .uri(uri)
                .headers(headersConsumer)
                .retrieve()
                .onStatus(restResponseErrorHandler)
                .body(responseType);
    }

//    public <T> T sendPostRequestSimple(String requestUri, Map<String, Object> variables, Consumer<HttpHeaders> headersConsumer, Class<T> responseType){
//        URI uri;
//        if (variables != null)
//            uri = UriComponentsBuilder.fromHttpUrl(requestUri).buildAndExpand(variables).toUri();
//        else
//            uri = URI.create(requestUri);
//        return restClient.get()
//                .uri(uri)
//                .headers(headersConsumer)
//                .retrieve()
//                .onStatus(restResponseErrorHandler)
//                .body(responseType);
//    }


}
