package com.vbhoomidi.service;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VehicleListService {
    List<VehicleInfo> findAll();
    VehicleInfo findOne(String id);
    VehicleInfo findbyVin(String vin);
    void create(VehicleInfo[] vehicle);
}
