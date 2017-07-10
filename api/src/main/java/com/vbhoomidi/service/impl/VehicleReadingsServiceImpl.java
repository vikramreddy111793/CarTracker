package com.vbhoomidi.service.impl;

import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.exception.ResourceNotFoundException;
import com.vbhoomidi.repository.VehicleReadingsRepository;
import com.vbhoomidi.service.VehicleReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Service
public class VehicleReadingsServiceImpl implements VehicleReadingsService {

    @Autowired
    private VehicleReadingsRepository repository;

    public List<VehicleReadings> findReadingsofVehicle(String vin) {
        List<VehicleReadings> existinglist = repository.findReadingsbyVin(vin);
        if(existinglist == null || existinglist.size()==0){
            throw new ResourceNotFoundException("This vehicle with vin-"+vin+" doesn't have any readings");
        }
        return existinglist;
    }

    public void create(VehicleReadings readings) {
        repository.tireCreate(readings.getTires());
        repository.readingsCreate(readings);
    }
}
