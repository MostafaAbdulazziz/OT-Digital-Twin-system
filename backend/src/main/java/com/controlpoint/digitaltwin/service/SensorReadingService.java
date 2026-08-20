package com.controlpoint.digitaltwin.service;

import com.controlpoint.digitaltwin.dto.SensorReadingResponseDto;

public interface SensorReadingService {
    SensorReadingResponseDto getLatestReadingForAsset(Long assetId);
}