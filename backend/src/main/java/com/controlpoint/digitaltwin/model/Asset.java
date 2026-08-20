package com.controlpoint.digitaltwin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

}
