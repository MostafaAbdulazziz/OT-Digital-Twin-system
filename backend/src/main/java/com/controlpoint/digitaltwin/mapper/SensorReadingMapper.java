package com.controlpoint.digitaltwin.mapper;

import com.controlpoint.digitaltwin.dto.SensorReadingDto;
import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.model.SensorReading;
import org.springframework.stereotype.Component;

@Component
public class SensorReadingMapper {
    public SensorReadingDto toSensorReadingDto(SensorReading sensorReading)
    {
        if(sensorReading == null)
        {
            return null;
        }

        return new SensorReadingDto(
                sensorReading.getId(),
                sensorReading.getTemperature(),
                sensorReading.getPressure(),
                sensorReading.getTimestamp(),
                sensorReading.getAsset() != null ? sensorReading.getAsset().getId() : null
        );
    }

    public SensorReading toSensorReading(SensorReadingDto sensorReadingDto)
    {
        if(sensorReadingDto == null) return null;

        SensorReading sensorReading = new SensorReading();
        sensorReading.setTemperature(sensorReadingDto.getTemperature());
        sensorReading.setPressure(sensorReadingDto.getPressure());
        sensorReading.setTimestamp(sensorReadingDto.getTimestamp());

        if(sensorReadingDto.getAssetId() != null)
        {
            Asset asset = new Asset();
            asset.setId(sensorReadingDto.getAssetId());
            sensorReading.setAsset(asset);
        }

        return sensorReading;
    }
}
