package by.froleod.healthdataservice.repository;

import by.froleod.healthdataservice.model.HealthData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface HealthDataRepository extends ReactiveCrudRepository<HealthData, Long> {
    Flux<HealthData> findByDeviceId(String deviceId);
}
