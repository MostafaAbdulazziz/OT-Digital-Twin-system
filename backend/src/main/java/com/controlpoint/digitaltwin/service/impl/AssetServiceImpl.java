package com.controlpoint.digitaltwin.service.impl;

import com.controlpoint.digitaltwin.dto.AssetRequestDto;
import com.controlpoint.digitaltwin.dto.AssetResponseDto;
import com.controlpoint.digitaltwin.exception.ResourceNotFoundException;
import com.controlpoint.digitaltwin.mapper.AssetMapper;
import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.repository.AssetRepository;
import com.controlpoint.digitaltwin.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    @Override
    public List<AssetResponseDto> getAllAssets() {
        return assetRepository.findAll().stream()
                .map(assetMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AssetResponseDto getAssetById(Long id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id: " + id));
        return assetMapper.toResponseDto(asset);
    }

    @Override
    public AssetResponseDto createAsset(AssetRequestDto request) {
        Asset newAsset = assetMapper.toEntity(request);
        Asset savedAsset = assetRepository.save(newAsset);
        return assetMapper.toResponseDto(savedAsset);
    }
}