package by.froleod.healthdataservice;

import by.froleod.healthdataservice.model.HealthData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class HealthDataServiceTests {

    @Autowired
    WebTestClient client;

    @Test
    void testSaveAndGet() {
        HealthData data = HealthData.builder()
                .deviceId("tracker-1")
                .heartRate(85)
                .steps(1200)
                .calories(75.5)
                .build();

        client.post().uri("/api/health-data")
                .bodyValue(data)
                .exchange()
                .expectStatus().isOk();

        client.get().uri("/api/health-data")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(HealthData.class)
                .hasSize(1);
    }
}
