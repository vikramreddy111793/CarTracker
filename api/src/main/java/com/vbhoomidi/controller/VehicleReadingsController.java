package com.vbhoomidi.controller;

import com.vbhoomidi.FacadeService.VehicleReadingAlertFacade;
import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */

@RestController
@RequestMapping(value = "/readings")
@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
public class VehicleReadingsController {

    @Autowired
    private VehicleReadingAlertFacade facadeService;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException e){
        return e.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public List<VehicleReadings> findReadingsofVehicle(@PathVariable("id") String id){
        return facadeService.findReadingsofVehicle(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody VehicleReadings readings){
        facadeService.createReadings(readings);
    }
}
