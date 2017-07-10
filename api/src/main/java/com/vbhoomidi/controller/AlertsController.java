package com.vbhoomidi.controller;

import com.vbhoomidi.FacadeService.VehicleReadingAlertFacade;
import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
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
    private VehicleReadingAlertFacade facadeService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public List<Alert> findAlertsofVehicle(@PathVariable("id") String id){
        return facadeService.findAlertsofVehicle(id);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/high")
    public Map<VehicleInfo, Integer> countHighAlerts(){
        return facadeService.countHighAlerts();
    }
}
