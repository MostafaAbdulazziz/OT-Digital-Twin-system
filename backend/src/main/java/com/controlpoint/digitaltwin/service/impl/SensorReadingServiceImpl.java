package com.controlpoint.digitaltwin.service.impl;

import com.controlpoint.digitaltwin.dto.SensorReadingResponseDto;
import com.controlpoint.digitaltwin.exception.ResourceNotFoundException;
import com.controlpoint.digitaltwin.mapper.SensorReadingMapper;
import com.controlpoint.digitaltwin.repository.AssetRepository;
import com.controlpoint.digitaltwin.repository.SensorReadingRepository;
import com.controlpoint.digitaltwin.service.SensorReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorReadingServiceImpl implements SensorReadingService {

    private final SensorReadingRepository sensorReadingRepository;
    private final AssetRepository assetRepository;
    private final SensorReadingMapper sensorReadingMapper;

    @Override
    public SensorReadingResponseDto getLatestReadingForAsset(Long assetId) {
        // First verify the asset exists
        if (!assetRepository.existsById(assetId)) {
            throw new ResourceNotFoundException("Asset not found with id: " + assetId);
        }

        // Fetch the latest reading and map it to a DTO
        return sensorReadingRepository.findTopByAssetIdOrderByTimestampDesc(assetId)
                .map(sensorReadingMapper::toResponseDto)
                .orElse(null);
    }
}