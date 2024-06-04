package org.farpost.farpostapi2.api;

import org.farpost.farpostapi2.dto.client_dto.ClientTgDto;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class ClientApiTest {

    private static RestClient restClient;

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @BeforeAll
    public static void init() {
        restClient = RestClient.builder()
//                .baseUrl("http://localhost:8080/client")
                .build();
    }

    @Test
    public void sendUpdateCityRequests() {

        for (int i = 0; i < 2; i++) {
            if (i == 0)
                executorService.execute(() -> {
                    //                    ClientDto clientDto = new ClientDto("aboba", "ad", new ArrayList<>());
                    restClient.delete()
                            .uri("http://localhost:8080/client/67")
                            //                            .body(clientDto)
                            .retrieve();
                    logger.info("success1");

                });
            else
                executorService.execute(() -> {
                    ClientTgDto clientTgDto = new ClientTgDto(123213213, "@SuMaasdkar777");
                    HttpStatusCode status = restClient.put()
                            .uri("http://localhost:8080/tg/145")
                            .body(clientTgDto)
                            .contentType(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .toBodilessEntity()
                                    .getStatusCode();
                    logger.info("success2" + status.value());
                });
        }
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
