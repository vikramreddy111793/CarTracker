package com.vbhoomidi.service.impl;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.repository.AlertRepository;
import com.vbhoomidi.repository.VehicleListRepository;
import com.vbhoomidi.repository.VehicleReadingsRepository;
import com.vbhoomidi.service.VehicleReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Service
public class VehicleReadingsServiceImpl implements VehicleReadingsService {

    @Autowired
    private VehicleReadingsRepository repository;

    public List<VehicleReadings> findReadingsofVehicle(String vin) {

        return repository.findReadingsbyVin(vin);
    }

    public void create(VehicleReadings readings) {
        repository.tireCreate(readings.getTires());
        repository.readingsCreate(readings);
    }
}
