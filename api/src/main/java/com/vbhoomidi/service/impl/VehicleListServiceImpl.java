package com.vbhoomidi.service.impl;


import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.repository.AlertRepository;
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
        return repository.findAll();
    }

    public VehicleInfo findOne(String id) {
        return repository.findOne(id);
    }

    public VehicleInfo findbyVin(String vin) {
        return repository.findbyVin(vin);
    }

    @Transactional
    public void create(VehicleInfo[] vehicle) {
        for(int i=0; i< vehicle.length; i++){
            VehicleInfo existing = repository.findbyVin((vehicle[i]).getVin());
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
