package com.controlpoint.digitaltwin.mapper;

import com.controlpoint.digitaltwin.dto.AssetRequestDto;
import com.controlpoint.digitaltwin.dto.AssetResponseDto;
import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.model.AssetStatus;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper {
    public AssetResponseDto toResponseDto(Asset asset) {
        if (asset == null) return null;
        return new AssetResponseDto(
                asset.getId(),
                asset.getName(),
                asset.getType(),
                asset.getStatus() != null ? asset.getStatus().name() : null
        );
    }

    public Asset toEntity(AssetRequestDto request) {
        if (request == null) return null;
        AssetStatus parsedStatus = AssetStatus.STOPPED;
        if (request.status() != null && !request.status().trim().isEmpty()) {
            try {
                parsedStatus = AssetStatus.valueOf(request.status().toUpperCase());
            } catch (IllegalArgumentException ignored) {

            }
        }

        return Asset.builder()
                .name(request.name())
                .type(request.type())
                .status(parsedStatus)
                .build();
    }
}