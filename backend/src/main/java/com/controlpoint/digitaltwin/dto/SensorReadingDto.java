package com.controlpoint.digitaltwin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SensorReadingDto {
    private Long id;

    private Double temperature;

    private Double pressure;

    private LocalDateTime timestamp;

    private Long assetId;
}
