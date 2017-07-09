package com.vbhoomidi.controller;

import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.service.VehicleReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */

@RestController
@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
public class VehicleReadingsController {

    @Autowired
    private VehicleReadingsService service;

    @RequestMapping(method = RequestMethod.GET, value = "/readings/{id}")
    public List<VehicleReadings> findReadingsofVehicle(@PathVariable("id") String id){
        return service.findReadingsofVehicle(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/readings")
    public void create(@RequestBody VehicleReadings readings){
        service.create(readings);
    }
}
