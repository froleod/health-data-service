package by.froleod.healthdataservice.service;

import by.froleod.healthdataservice.model.HealthData;
import by.froleod.healthdataservice.repository.HealthDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HealthDataServiceTest {

    @Mock
    private HealthDataRepository repository;

    @InjectMocks
    private HealthDataService service;

    private HealthData sampleData;

    @BeforeEach
    void setup() {
        sampleData = new HealthData();
        sampleData.setUserId(UUID.randomUUID());
        sampleData.setDeviceId("device-1");
        sampleData.setSteps(42);
        sampleData.setTimestamp(Instant.now());
    }

    @Test
    void saveData_shouldSave() {
        when(repository.save(any(HealthData.class))).thenReturn(Mono.just(sampleData));

        Mono<HealthData> result = service.saveData(sampleData);

        StepVerifier.create(result)
                .expectNextMatches(data -> data.getSteps() == 42)
                .verifyComplete();

        verify(repository, times(1)).save(sampleData);
    }

    @Test
    void getAllData_shouldReturnFlux() {
        when(repository.findAll()).thenReturn(Flux.just(sampleData));

        StepVerifier.create(service.getAllData())
                .expectNext(sampleData)
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }

    @Test
    void getById_shouldReturnMono() {
        when(repository.findById(anyLong())).thenReturn(Mono.just(sampleData));

        StepVerifier.create(service.getById("1"))
                .expectNext(sampleData)
                .verifyComplete();

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void delete_shouldCallRepository() {
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        StepVerifier.create(service.delete("1"))
                .verifyComplete();

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void getByUser_shouldReturnFlux() {
        UUID userId = sampleData.getUserId();
        when(repository.findByUserId(userId)).thenReturn(Flux.just(sampleData));

        StepVerifier.create(service.getByUser(userId))
                .expectNext(sampleData)
                .verifyComplete();

        verify(repository, times(1)).findByUserId(userId);
    }
}
