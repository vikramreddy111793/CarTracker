package com.vbhoomidi.controller;

import com.vbhoomidi.FacadeService.VehicleReadingAlertFacade;
import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException e){
        return e.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public List<Alert> findAlertsofVehicle(@PathVariable("id") String id){
        return facadeService.findAlertsofVehicle(id);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/high")
    public Map<String, Integer> countHighAlerts(){
        return facadeService.countHighAlerts();
    }
}
