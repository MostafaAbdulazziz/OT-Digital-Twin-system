package com.controlpoint.digitaltwin.dto;

public record SensorReadingRequestDto(Long assetId, Double temperature, Double pressure) {}