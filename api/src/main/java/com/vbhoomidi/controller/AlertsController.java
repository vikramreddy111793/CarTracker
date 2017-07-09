package com.vbhoomidi.controller;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.service.VehicleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@RestController
@RequestMapping(value = "/alerts")
public class AlertsController {

    @Autowired
    VehicleListService vehicleService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public List<Alert> findAlertsofVehicle(@PathVariable("id") String id){
        return vehicleService.findAlertsofVehicle(id);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/high")
    public Map<VehicleInfo, Integer> countHighAlerts(){
        return vehicleService.countHighAlerts();
    }
}
