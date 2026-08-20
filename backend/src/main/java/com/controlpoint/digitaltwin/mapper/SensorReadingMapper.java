package com.controlpoint.digitaltwin.mapper;

import com.controlpoint.digitaltwin.dto.SensorReadingRequestDto;
import com.controlpoint.digitaltwin.dto.SensorReadingResponseDto;
import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.model.SensorReading;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SensorReadingMapper {
    public SensorReadingResponseDto toResponseDto(SensorReading reading) {
        if (reading == null) return null;
        return new SensorReadingResponseDto(
                reading.getId(),
                reading.getTemperature(),
                reading.getPressure(),
                reading.getTimestamp()
        );
    }

    public SensorReading toEntity(SensorReadingRequestDto request, Asset asset) {
        if (request == null) return null;
        return SensorReading.builder()
                .temperature(request.temperature())
                .pressure(request.pressure())
                .timestamp(LocalDateTime.now())
                .asset(asset)
                .build();
    }
}