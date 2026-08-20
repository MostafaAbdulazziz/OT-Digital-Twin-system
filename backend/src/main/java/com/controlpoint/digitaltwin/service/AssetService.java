package com.controlpoint.digitaltwin.service;

import com.controlpoint.digitaltwin.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface AssetService {
    void CreatAsset(AssetDto assetDto);
}
