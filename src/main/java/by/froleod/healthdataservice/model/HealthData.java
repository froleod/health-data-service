package by.froleod.healthdataservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("health_data")
public class HealthData {
    @Id
    private Long id;
    private String deviceId;
    private int heartRate;
    private int steps;
    private double calories;
    private Instant timestamp;
}

