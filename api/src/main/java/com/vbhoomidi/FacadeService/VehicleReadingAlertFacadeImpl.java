package com.vbhoomidi.FacadeService;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.service.AlertService;
import com.vbhoomidi.service.VehicleListService;
import com.vbhoomidi.service.VehicleReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Service
public class VehicleReadingAlertFacadeImpl implements VehicleReadingAlertFacade{

    @Autowired
    private AlertService alertService;
    @Autowired
    private VehicleListService vehicleListService;
    @Autowired
    private VehicleReadingsService vehicleReadingsService;

    @Transactional
    public void createReadings(VehicleReadings readings) {
        vehicleReadingsService.create(readings);
        VehicleInfo vehicle = vehicleListService.findbyVin(readings.getVin());
        createAlerts(readings,vehicle);
    }

    @Transactional
    public void createAlerts(VehicleReadings readings, VehicleInfo vehicle) {
        if(readings.getEngineRpm()>vehicle.getRedlineRpm()){
            alertService.create(readings.getVin(), "HIGH", readings.getTimestamp());
        }
        if(readings.getFuelVolume()<((vehicle.getMaxFuelVolume())*0.1)){
            alertService.create(readings.getVin(), "MEDIUM", readings.getTimestamp());
        }
        if(readings.getTires().getRearLeft()<32 || readings.getTires().getRearLeft()>36 ||
                readings.getTires().getRearRight()<32 || readings.getTires().getRearRight()>36 ||
                readings.getTires().getFrontLeft()<32 || readings.getTires().getFrontLeft()>36 ||
                readings.getTires().getFrontRight()<32 || readings.getTires().getFrontRight()>36){
            alertService.create(readings.getVin(), "LOW", readings.getTimestamp());
        }
        if(readings.isEngineCoolantLow() || readings.isCheckEngineLightOn()){
            alertService.create(readings.getVin(), "LOW", readings.getTimestamp());
        }
    }

    public List<VehicleReadings> findReadingsofVehicle(String id) {
        VehicleInfo vehicle = vehicleListService.findOne(id);
        return vehicleReadingsService.findReadingsofVehicle(vehicle.getVin());
    }

    public List<Alert> findAlertsofVehicle(String id) {
        VehicleInfo vehicle = vehicleListService.findOne(id);
        return alertService.findAlertsbyVin(vehicle.getVin());
    }

    public Map<VehicleInfo, Integer> countHighAlerts() {
        Map<VehicleInfo, Integer> highalertscount = new HashMap<VehicleInfo, Integer>();
        List<VehicleInfo> vehicleslist = vehicleListService.findAll();
        for(VehicleInfo v: vehicleslist){
            int count = alertService.countHighAlertsbyVin(v.getVin());
            highalertscount.put(v,count);
        }
        return highalertscount;
    }
}
