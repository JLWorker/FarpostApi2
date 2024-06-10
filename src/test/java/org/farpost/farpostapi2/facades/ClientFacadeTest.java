package org.farpost.farpostapi2.facades;

import org.farpost.farpostapi2.dto.client_dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class ClientFacadeTest {

    @Autowired
    private ClientFacade clientFacade;

    @Test
    public void testQueue(){
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        for (int i =0; i<8; i++)
            executorService.execute(() -> clientFacade.addNewClient(new ClientDto()));

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
