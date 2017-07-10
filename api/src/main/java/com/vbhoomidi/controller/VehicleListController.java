package com.vbhoomidi.controller;

import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.service.VehicleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicles")
@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
public class VehicleListController {

    @Autowired
    VehicleListService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<VehicleInfo> findAll(){
       return service.findAll();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public VehicleInfo findOne(@PathVariable("id") String vehicleID){
        return service.findOne(vehicleID);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public void create(@RequestBody VehicleInfo[] vehicle){
        service.create(vehicle);
    }

}
