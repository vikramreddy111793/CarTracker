package com.vbhoomidi.service;

import com.vbhoomidi.entity.VehicleReadings;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by vikramreddy on 7/9/2017.
 */
public interface VehicleReadingsService {
    List<VehicleReadings> findReadingsofVehicle(String vin);
    List<VehicleReadings> create(VehicleReadings readings);
    List<Map<String,Double>> findVehicleGeoLocation(String vin);
}
