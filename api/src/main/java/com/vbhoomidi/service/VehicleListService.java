package com.vbhoomidi.service;

import com.vbhoomidi.entity.VehicleInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface VehicleListService {
    List<VehicleInfo> findAll();
    VehicleInfo findOne(String id);
    void create(VehicleInfo[] vehicle);
    Map<VehicleInfo, Integer> countHighAlerts();
}
