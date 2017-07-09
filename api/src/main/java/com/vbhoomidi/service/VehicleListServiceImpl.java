package com.vbhoomidi.service;


import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.repository.AlertRepository;
import com.vbhoomidi.repository.VehicleListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VehicleListServiceImpl implements VehicleListService {

    @Autowired
    private VehicleListRepository vehicleRepository;
    @Autowired
    private AlertRepository alertRepository;

    public List<VehicleInfo> findAll() {
        return vehicleRepository.findAll();
    }

    public VehicleInfo findOne(String id) {
        return vehicleRepository.findOne(id);
    }

    public Map<VehicleInfo, Integer> countHighAlerts(){

        Map<VehicleInfo, Integer> highalertscount = new HashMap<VehicleInfo, Integer>();
        List<VehicleInfo> vehicleslist = vehicleRepository.findAll();
        for(VehicleInfo v: vehicleslist){
            int count = alertRepository.countHighAlertsbyVin(v.getVin());
            highalertscount.put(v,count);
        }
        return highalertscount;
    }

    public List<Alert> findAlertsofVehicle(String id) {
        VehicleInfo vehicle = vehicleRepository.findOne(id);
        return alertRepository.findAlertsbyVin(vehicle.getVin());
    }

    @Transactional
    public void create(VehicleInfo[] vehicle) {
        for(int i=0; i< vehicle.length; i++){
            VehicleInfo existing = vehicleRepository.findbyVin((vehicle[i]).getVin());
            if(existing==null){
                vehicleRepository.create(vehicle[i]);
            }
            else{
                (vehicle[i]).setId(existing.getId());
                vehicleRepository.update(vehicle[i]);
            }
        }
    }
}
