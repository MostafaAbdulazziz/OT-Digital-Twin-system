package com.controlpoint.digitaltwin.service;

import com.controlpoint.digitaltwin.dto.AssetRequestDto;
import com.controlpoint.digitaltwin.dto.AssetResponseDto;
import com.controlpoint.digitaltwin.dto.SensorReadingResponseDto;

import java.util.List;

public interface AssetService {
    List<AssetResponseDto> getAllAssets();
    AssetResponseDto getAssetById(Long id);
    AssetResponseDto createAsset(AssetRequestDto request);
}