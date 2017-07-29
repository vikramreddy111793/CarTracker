package com.vbhoomidi.service;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VehicleListService {
    Optional<List<VehicleInfo>> findAll();
    VehicleInfo findOne(String id);
    Optional<VehicleInfo> findbyVin(String vin);
    List<VehicleInfo> create(VehicleInfo[] vehicle);
}
