package com.vbhoomidi.service.impl;


import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.exception.ResourceNotFoundException;
import com.vbhoomidi.repository.VehicleListRepository;
import com.vbhoomidi.service.VehicleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VehicleListServiceImpl implements VehicleListService {

    @Autowired
    private VehicleListRepository repository;

    @Cacheable(value = "vehicleslist", key = "'all'")
    public Optional<List<VehicleInfo>> findAll() {
        List<VehicleInfo> existinglist = repository.findAll();
        if(existinglist == null){
            return Optional.empty();
        }
        return Optional.of(existinglist);
    }

    @Cacheable(value = "vehiclesbyID", key = "#id")
    public VehicleInfo findOne(String id) {
        return repository.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id-"+id+" doesn't exist"));
    }

    public Optional<VehicleInfo> findbyVin(String vin) {
        List<VehicleInfo> existinglist = findAll().orElse(null);
        if(existinglist!=null){
            for(VehicleInfo v: existinglist){
                if (v.getVin()
                        .equals(vin)){
                    return Optional.of(v);
                }
            }
        }
        return Optional.empty();
    }

    @CachePut(value = "vehicleslist", key = "'all'")
    @Transactional
    public List<VehicleInfo> create(VehicleInfo[] vehicle) {
        for(int i=0; i< vehicle.length; i++){
            VehicleInfo existing = findbyVin((vehicle[i]).getVin()).orElse(null);
            if(existing==null){
                repository.create(vehicle[i]);
            }
            else{
                vehicle[i].setId(existing.getId());
                repository.update(vehicle[i]);
            }
        }
        return repository.findAll();
    }

}
