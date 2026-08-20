package com.controlpoint.digitaltwin.dto;

import com.controlpoint.digitaltwin.model.AssetStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssetDto {
    private Long id;

    private String name;

    private String type;

    private AssetStatus status;

}
