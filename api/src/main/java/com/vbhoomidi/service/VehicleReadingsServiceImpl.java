package com.vbhoomidi.service;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.repository.AlertRepository;
import com.vbhoomidi.repository.VehicleListRepository;
import com.vbhoomidi.repository.VehicleReadingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Service
public class VehicleReadingsServiceImpl implements VehicleReadingsService {

    @Autowired
    private VehicleReadingsRepository readingsRepository;
    @Autowired
    private VehicleListRepository vehicleRepository;
    @Autowired
    private AlertRepository alertRepository;

    public List<VehicleReadings> findAll() {
        return null;
    }

    public VehicleReadings findOne(String id) {
        return null;
    }

    @Transactional
    public void createAlert(String vin, String priority, Date timeStamp) {
        Alert alert = new Alert();
        alert.setPriority(priority);
        alert.setTimeStamp(timeStamp);
        alert.setVin(vin);
        alertRepository.create(alert);
    }

    @Transactional
    public void create(VehicleReadings readings) {
        readingsRepository.tireCreate(readings.getTires());
        readingsRepository.readingsCreate(readings);
        VehicleInfo vehicle = vehicleRepository.findbyVin(readings.getVin());
        if(readings.getEngineRpm()>vehicle.getRedlineRpm()){
            createAlert(readings.getVin(), "HIGH", readings.getTimestamp());
        }
        if(readings.getFuelVolume()<((vehicle.getMaxFuelVolume())*0.1)){
            createAlert(readings.getVin(), "MEDIUM", readings.getTimestamp());
        }
        if(readings.getTires().getRearLeft()<32 || readings.getTires().getRearLeft()>36 ||
        readings.getTires().getRearRight()<32 || readings.getTires().getRearRight()>36 ||
        readings.getTires().getFrontLeft()<32 || readings.getTires().getFrontLeft()>36 ||
        readings.getTires().getFrontRight()<32 || readings.getTires().getFrontRight()>36){
            createAlert(readings.getVin(), "LOW", readings.getTimestamp());
        }
        if(readings.isEngineCoolantLow() || readings.isCheckEngineLightOn()){
            createAlert(readings.getVin(), "LOW", readings.getTimestamp());
        }
    }
}
