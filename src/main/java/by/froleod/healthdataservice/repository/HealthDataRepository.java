package by.froleod.healthdataservice.repository;

import by.froleod.healthdataservice.model.HealthData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface HealthDataRepository extends ReactiveCrudRepository<HealthData, Long> {
    Flux<HealthData> findByDeviceId(String deviceId);

    Flux<HealthData> findByUserId(UUID userId);
}
