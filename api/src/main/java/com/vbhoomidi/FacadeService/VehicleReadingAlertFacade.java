package com.vbhoomidi.FacadeService;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.entity.VehicleReadings;

import java.util.List;
import java.util.Map;

/**
 * Created by vikramreddy on 7/9/2017.
 */
public interface VehicleReadingAlertFacade {
    void createReadings(VehicleReadings readings);
    void createAlerts(VehicleReadings readings, VehicleInfo vehicle);
    List<VehicleReadings> findReadingsofVehicle(String id);
    List<Alert> findAlertsofVehicle(String id);
    Map<VehicleInfo, Integer> countHighAlerts();
}
