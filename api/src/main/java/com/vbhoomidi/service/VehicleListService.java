package com.vbhoomidi.service;

import com.vbhoomidi.entity.VehicleInfo;

import java.util.Arrays;
import java.util.List;

public interface VehicleListService {
    List<VehicleInfo> findAll();
    VehicleInfo findOne(String id);
    void create(VehicleInfo[] vehicle);
}
