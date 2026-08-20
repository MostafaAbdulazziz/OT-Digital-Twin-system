package com.controlpoint.digitaltwin.scheduler;

import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.model.AssetStatus;
import com.controlpoint.digitaltwin.model.SensorReading;
import com.controlpoint.digitaltwin.repository.AssetRepository;
import com.controlpoint.digitaltwin.repository.SensorReadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SimulationScheduler {
    private final AssetRepository assetRepository;
    private final SensorReadingRepository sensorReadingRepository;

    private final Random random = new Random();

    @Scheduled(fixedRate = 5000)
    public void simulateSensorData() {
        List<Asset> assets = assetRepository.findAll();
        for (Asset asset : assets) {
            double temp = 20.0 + (random.nextDouble() * 80.0);
            double pressure = 1.0 + (random.nextDouble() * 9.0);

            int statusRoll = random.nextInt(10);
            asset.setStatus(statusRoll > 8 ? AssetStatus.ALARM : (statusRoll > 7 ? AssetStatus.STOPPED : AssetStatus.RUNNING));
            assetRepository.save(asset);

            SensorReading reading = SensorReading.builder()
                    .asset(asset).temperature(temp)
                    .pressure(pressure)
                    .timestamp(LocalDateTime.now())
                    .build();
            sensorReadingRepository.save(reading);
        }
    }
}