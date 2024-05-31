package org.farpost.farpostapi2.api;

import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;
import org.farpost.farpostapi2.dto.client_dto.ClientDto;
import org.farpost.farpostapi2.dto.client_dto.ClientTelegramDto;
import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.enitities.Client;
import org.farpost.farpostapi2.enitities.ClientTg;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientApiTest {

    private static RestClient restClient;

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @BeforeAll
    public static void init(){
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/client")
                .build();
    }

    @Test
    public void sendUpdateCityRequests(){

        for (int i = 0; i<2; i++)
            if (i == 0)
                executorService.execute(() -> {
//                    ClientDto clientDto = new ClientDto("aboba", "ad", new ArrayList<>());
                    Client city = restClient.put()
                            .uri("/3")
//                            .body(clientDto)
                            .retrieve()
                            .body(Client.class);
                    logger.info(city.getName());
                });
            else
                executorService.execute(() -> {
                    ClientTelegramDto clientTelegramDto = new ClientTelegramDto(123213213, "asdasdasd");
                    ClientTg city = restClient.put()
                            .uri("/tgs/2")
                            .body(clientTelegramDto)
                            .retrieve()
                            .body(ClientTg.class);
                    logger.info(city.getUsername());
                });
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
