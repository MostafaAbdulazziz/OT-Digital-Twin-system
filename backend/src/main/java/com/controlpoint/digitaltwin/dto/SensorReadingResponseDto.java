package com.controlpoint.digitaltwin.dto;

import java.time.LocalDateTime;

public record SensorReadingResponseDto(Long id, Double temperature, Double pressure, LocalDateTime timestamp) {}