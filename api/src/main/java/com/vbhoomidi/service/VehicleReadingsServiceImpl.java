package com.vbhoomidi.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vbhoomidi.entity.Tire;
import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.repository.VehicleReadingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Service
public class VehicleReadingsServiceImpl implements VehicleReadingsService {

    @Autowired
    private VehicleReadingsRepository repository;

    public List<VehicleReadings> findAll() {
        return null;
    }

    public VehicleReadings findOne(String id) {
        return null;
    }

    @Transactional
    public void create(VehicleReadings readings) {
        repository.tireCreate(readings.getTires());
        repository.readingsCreate(readings);
    }
}
