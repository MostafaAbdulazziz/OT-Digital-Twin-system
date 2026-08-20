package com.controlpoint.digitaltwin.mapper;

import com.controlpoint.digitaltwin.dto.AssetDto;
import com.controlpoint.digitaltwin.model.Asset;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper {
    public AssetDto toAssetDto(Asset asset)
    {
        if(asset == null)
        {
            return null;
        }
        return new AssetDto(asset.getId(),
                asset.getName(),
                asset.getType(),
                asset.getStatus()
        );
    }

    public Asset toAsset(AssetDto assetDto)
    {
        if(assetDto == null) return null;
        Asset asset = new Asset();
        asset.setName(assetDto.getName());
        asset.setType(assetDto.getType());
        asset.setStatus(assetDto.getStatus());
        return asset;
    }
}
