package com.vbhoomidi.controller;

import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.service.VehicleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
public class VehicleListController {

    @Autowired
    VehicleListService service;

    @RequestMapping(method = RequestMethod.GET, value = "/vehicles")
    public List<VehicleInfo> findAll(){
       return service.findAll();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/vehicles/{id}")
    public VehicleInfo findOne(@PathVariable("id") String vehicleID){
        return service.findOne(vehicleID);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicles")
    public void create(@RequestBody List<VehicleInfo> vehicle){
        service.create(vehicle);
    }

}
