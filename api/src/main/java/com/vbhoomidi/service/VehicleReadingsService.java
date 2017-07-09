package com.vbhoomidi.service;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.entity.VehicleReadings;

import java.util.Date;
import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
public interface VehicleReadingsService {
//    List<VehicleReadings> findAll();
//    VehicleReadings findOne(String id);
    void create(VehicleReadings readings);
    void createAlert(String vin, String priority, Date timeStamp);
}
