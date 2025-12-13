package by.froleod.healthdataservice.controller;

import by.froleod.healthdataservice.controller.HealthDataController;
import by.froleod.healthdataservice.model.HealthData;
import by.froleod.healthdataservice.service.HealthDataService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(HealthDataController.class)
class HealthDataControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private HealthDataService service;

    @Test
    void save_shouldReturnSavedData() {
        HealthData data = new HealthData();
        data.setUserId(UUID.randomUUID());
        data.setSteps(55);
        data.setDeviceId("device-1");
        data.setTimestamp(Instant.now());

        when(service.saveData(any(HealthData.class))).thenReturn(Mono.just(data));

        webClient.post().uri("/api/health-data")
                .header("X-User-Id", data.getUserId().toString())
                .bodyValue(data)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.steps").isEqualTo(55);
    }

    @Test
    void getAll_shouldReturnFlux() {
        HealthData data = new HealthData();
        data.setUserId(UUID.randomUUID());
        data.setSteps(42);
        data.setDeviceId("device-1");
        data.setTimestamp(Instant.now());

        when(service.getAllData()).thenReturn(Flux.just(data));

        webClient.get().uri("/api/health-data")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].steps").isEqualTo(42);
    }
}
