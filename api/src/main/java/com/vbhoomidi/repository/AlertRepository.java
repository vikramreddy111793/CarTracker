package com.vbhoomidi.repository;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;

import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
public interface AlertRepository {
    List<Alert> findAll();
    Alert findOne(String id);
    void create(Alert alert);
    List<Alert> findbyVin(String vin);
}
