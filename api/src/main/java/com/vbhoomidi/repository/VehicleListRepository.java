package com.vbhoomidi.repository;

import com.vbhoomidi.entity.VehicleInfo;

import java.util.List;

public interface VehicleListRepository {
    List<VehicleInfo> findAll();
    VehicleInfo findOne(String id);
    void create(VehicleInfo vehicle);
    VehicleInfo findbyVin(String vin);
    void update(VehicleInfo vehicle);
}
