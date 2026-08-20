package com.controlpoint.digitaltwin.config;

import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.model.AssetStatus;
import com.controlpoint.digitaltwin.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final AssetRepository assetRepository;

    @Override
    public void run(String... args) throws Exception {
        if (assetRepository.count() == 0) {

            Asset pump = Asset.builder()
                    .name("Test Water Pump")
                    .type("Pump")
                    .status(AssetStatus.RUNNING)
                    .build();

            Asset conveyor = Asset.builder()
                    .name(" Assembly Conveyor")
                    .type("Conveyor")
                    .status(AssetStatus.STOPPED)
                    .build();

            Asset motor = Asset.builder()
                    .name("Fan Motor")
                    .type("Motor")
                    .status(AssetStatus.RUNNING)
                    .build();

            assetRepository.saveAll(List.of(pump, conveyor, motor));
        }
    }
}