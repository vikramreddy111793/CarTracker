package com.vbhoomidi.service.impl;


import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.exception.ResourceNotFoundException;
import com.vbhoomidi.repository.VehicleListRepository;
import com.vbhoomidi.service.VehicleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VehicleListServiceImpl implements VehicleListService {

    @Autowired
    private VehicleListRepository repository;

    public List<VehicleInfo> findAll() {
        List<VehicleInfo> existinglist = repository.findAll();
        if(existinglist == null){
            throw new ResourceNotFoundException("No List of Vehicles exist");
        }
        return existinglist;
    }

    public VehicleInfo findOne(String id) {
        return repository.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id-"+id+" doesn't exist"));
    }

    public VehicleInfo findbyVin(String vin) {
        return repository.findbyVin(vin)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with vin-"+vin+" doesn't exist"));
    }

    @Transactional
    public void create(VehicleInfo[] vehicle) {
        for(int i=0; i< vehicle.length; i++){
            VehicleInfo existing = repository.findbyVin((vehicle[i]).getVin()).orElse(null);
            if(existing==null){
                repository.create(vehicle[i]);
            }
            else{
                (vehicle[i]).setId(existing.getId());
                repository.update(vehicle[i]);
            }
        }
    }
}
