package com.vbhoomidi.service;


import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.repository.VehicleListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleListServiceImpl implements VehicleListService {

    @Autowired
    VehicleListRepository repository;

    public List<VehicleInfo> findAll() {
        return repository.findAll();
    }

    public VehicleInfo findOne(String id) {
        return repository.findOne(id);
    }

    @Transactional
    public void create(List<VehicleInfo> vehicle) {
        for(int i=0; i< vehicle.size(); i++){
            VehicleInfo existing = repository.findbyVin((vehicle.get(i)).getVin());
            if(existing==null){
                repository.create(vehicle.get(i));
            }
            else{
                (vehicle.get(i)).setId(existing.getId());
                repository.update(vehicle.get(i));
            }
        }
    }
}
