package by.froleod.healthdataservice.service;

import by.froleod.healthdataservice.model.HealthData;
import by.froleod.healthdataservice.repository.HealthDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class HealthDataService {
    private final HealthDataRepository repository;

    public Mono<HealthData> saveData(HealthData data) {
        data.setTimestamp(Instant.now());
        return repository.save(data)
                .doOnNext(d -> System.out.println("Сохранено: " + d))
                .onErrorResume(e -> {
                    System.err.println("Ошибка при сохранении: " + e.getMessage());
                    return Mono.empty();
                });
    }

    public Flux<HealthData> getAllData() {
        return repository.findAll()
                .limitRate(5) // демонстрация backpressure
                .log();
    }

    public Flux<HealthData> getByDevice(String deviceId) {
        return repository.findByDeviceId(deviceId);
    }

    public Mono<HealthData> getById(String id) {
        return repository.findById(Long.valueOf(id));
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(Long.valueOf(id));
    }
}
