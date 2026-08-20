package com.controlpoint.digitaltwin.controller;

import com.controlpoint.digitaltwin.dto.AssetRequestDto;
import com.controlpoint.digitaltwin.dto.AssetResponseDto;
import com.controlpoint.digitaltwin.dto.SensorReadingResponseDto;
import com.controlpoint.digitaltwin.service.AssetService;
import com.controlpoint.digitaltwin.service.SensorReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;
    private final SensorReadingService sensorReadingService;

    @GetMapping
    public ResponseEntity<List<AssetResponseDto>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetResponseDto> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAssetById(id));
    }

    @PostMapping
    public ResponseEntity<AssetResponseDto> createAsset(@RequestBody AssetRequestDto request) {
        AssetResponseDto createdAsset = assetService.createAsset(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAsset);
    }

    @GetMapping("/{id}/readings/latest")
    public ResponseEntity<SensorReadingResponseDto> getLatestReading(@PathVariable Long id) {
        SensorReadingResponseDto reading = sensorReadingService.getLatestReadingForAsset(id);
        return ResponseEntity.ok(reading);
    }
}