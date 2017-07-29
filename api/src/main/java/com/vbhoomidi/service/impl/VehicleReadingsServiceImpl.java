package com.vbhoomidi.service.impl;


import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.exception.ResourceNotFoundException;
import com.vbhoomidi.repository.VehicleReadingsRepository;
import com.vbhoomidi.service.VehicleReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Service
public class VehicleReadingsServiceImpl implements VehicleReadingsService {

    @Autowired
    private VehicleReadingsRepository repository;

    @Cacheable(value = "readingsbyVIN", key = "#vin")
    public List<VehicleReadings> findReadingsofVehicle(String vin) {
        List<VehicleReadings> existinglist = repository.findReadingsbyVin(vin);
        if(existinglist == null){
            throw new ResourceNotFoundException("This vehicle with vin-"+vin+" doesn't have any readings");
        }
        return existinglist;
    }

    @CachePut(value = "readingsbyVIN", key = "#readings.vin")
    public List<VehicleReadings> create(VehicleReadings readings) {
        repository.tireCreate(readings.getTires());
        repository.readingsCreate(readings);
        return repository.findReadingsbyVin(readings.getVin());
    }

    public List<Map<String,Double>> findVehicleGeoLocation(String vin) {
        List<VehicleReadings> completeList =findReadingsofVehicle(vin);
        List<Map<String,Double>> vehicleGeoLocation = new ArrayList<>();
        if(completeList != null){
            for(VehicleReadings reading : completeList){
                Map<String,Double> location = new HashMap<>();
                Calendar previous = Calendar.getInstance();
                Calendar current = Calendar.getInstance();
                previous.setTime(reading.getTimestamp());
                long difference = current.getTimeInMillis()-previous.getTimeInMillis();
                if(difference<=30*1000){
                    location.put("lat",reading.getLatitude());
                    location.put("lng",reading.getLongitude());
                    vehicleGeoLocation.add(location);
                }

            }
        }
        return vehicleGeoLocation;
    }
}
