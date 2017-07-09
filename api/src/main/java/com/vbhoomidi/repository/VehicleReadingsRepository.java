package com.vbhoomidi.repository;

import com.vbhoomidi.entity.Tire;
import com.vbhoomidi.entity.VehicleReadings;

import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
public interface VehicleReadingsRepository {

    void readingsCreate(VehicleReadings readings);
    Tire tireCreate(Tire tire);
    List<VehicleReadings> findReadingsbyVin(String vin);
}
