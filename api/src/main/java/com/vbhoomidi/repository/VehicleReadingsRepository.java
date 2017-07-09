package com.vbhoomidi.repository;

import com.vbhoomidi.entity.Tire;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.entity.VehicleReadings;

import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
public interface VehicleReadingsRepository {

    List<VehicleReadings> findAll();
    VehicleReadings findOne(String id);
    void readingsCreate(VehicleReadings readings);
    Tire tireCreate(Tire tire);
    VehicleReadings findbyVin(String vin);
    void update(VehicleReadings vehicle);
}
