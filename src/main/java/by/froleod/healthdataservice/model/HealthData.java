package by.froleod.healthdataservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("health_data")
public class HealthData {
    @Id
    private Long id;
    private UUID userId;
    private String deviceId;
    private Integer heartRate;
    private Integer steps;
    private Double calories;
    private Integer systolicPressure;
    private Integer diastolicPressure;
    private Double glucose;
    private Integer spo2;
    private String source; // wearable / manual
    private Instant timestamp;
}

