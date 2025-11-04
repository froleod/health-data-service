package by.froleod.healthdataservice.controller;

import by.froleod.healthdataservice.model.HealthData;
import by.froleod.healthdataservice.service.HealthDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/health-data")
@RequiredArgsConstructor
public class HealthDataController {
    private final HealthDataService service;

    @PostMapping
    public Mono<HealthData> save(@RequestBody HealthData data) {
        return service.saveData(data);
    }

    @GetMapping
    public Flux<HealthData> getAll() {
        return service.getAllData();
    }

    @GetMapping("/{id}")
    public Mono<HealthData> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/device/{deviceId}")
    public Flux<HealthData> getByDevice(@PathVariable String deviceId) {
        return service.getByDevice(deviceId);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
