package com.vbhoomidi.repository;

import com.vbhoomidi.entity.VehicleInfo;

import java.util.List;
import java.util.Optional;

public interface VehicleListRepository {
    List<VehicleInfo> findAll();
    Optional<VehicleInfo> findOne(String id);
    void create(VehicleInfo vehicle);
    VehicleInfo update(VehicleInfo vehicle);
}
