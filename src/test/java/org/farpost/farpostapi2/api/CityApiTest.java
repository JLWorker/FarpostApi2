//package org.farpost.farpostapi2.api;
//
//import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;
//import org.farpost.farpostapi2.enitities.City;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import org.springframework.web.client.RestClient;
//
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.RunnableFuture;
//import java.util.logging.Logger;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("dev")
//public class CityApiTest {
//
//    private static RestClient restClient;
//
//    private final Logger logger = Logger.getLogger(this.getClass().getName());
//    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//    @BeforeAll
//    public static void init(){
//        restClient = RestClient.builder()
//                .baseUrl("http://localhost:8080/city")
//                .build();
//    }
//
//    @Test
//    public void sendUpdateCityRequests(){
//
//        for (int i = 0; i<5; i++)
//            executorService.execute(() -> {
//                ViewDrCityDto viewDrCityDto = new ViewDrCityDto("Charcov", 12);
//                City city = restClient.put()
//                        .uri("/3")
//                        .body(viewDrCityDto)
//                        .retrieve()
//                        .body(City.class);
//                logger.info(city.getName());
//            });
//
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Test
//    public void sendDeleteCityRequests(){
//
//        for(int i = 0; i<5; i++)
//            executorService.execute(() -> {
//                City city = restClient.delete()
//                        .uri("/3")
//                        .retrieve()
//                        .body(City.class);
//                logger.info(city.getName());
//            });
//
//    }
//
//}
