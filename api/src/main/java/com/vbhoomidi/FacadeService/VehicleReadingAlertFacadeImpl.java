package com.vbhoomidi.FacadeService;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.entity.Email;
import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.entity.VehicleReadings;
import com.vbhoomidi.exception.ResourceNotFoundException;
import com.vbhoomidi.service.AlertService;
import com.vbhoomidi.service.VehicleListService;
import com.vbhoomidi.service.VehicleReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Service
@PropertySource(value = "classpath:application.properties")
public class VehicleReadingAlertFacadeImpl implements VehicleReadingAlertFacade{

    @Autowired
    private AlertService alertService;
    @Autowired
    private VehicleListService vehicleListService;
    @Autowired
    private VehicleReadingsService vehicleReadingsService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Environment env;

    @Transactional
    public void createReadings(VehicleReadings readings) {
        vehicleReadingsService.create(readings);
        VehicleInfo vehicle = vehicleListService.findbyVin(readings.getVin())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with vin-"+readings.getVin()+" doesn't exist"));;
        createAlerts(readings,vehicle);
    }


    @Transactional
    public void createAlerts(VehicleReadings readings, VehicleInfo vehicle) {
        if(readings.getEngineRpm()>vehicle.getRedlineRpm()){
            alertService.create(readings.getVin(), "HIGH", readings.getTimestamp());
            Email email = new Email();
            email.setEmailMessage("High Alert for Car with Vin - "+vehicle.getVin()+"\n Make: "+vehicle.getMake()+"\n Model: "+vehicle.getModel());
            email.setReceiverEmail(env.getProperty("receiver.useremail"));
            email.setSenderEmail(env.getProperty("javamail.username"));
            jmsTemplate.convertAndSend("highalertemailqueue",email);
        }
        if(readings.getFuelVolume()<((vehicle.getMaxFuelVolume())*0.1)){
            alertService.create(readings.getVin(), "MEDIUM", readings.getTimestamp());
        }
        if(readings.getTires().getRearLeft()<32 || readings.getTires().getRearLeft()>36 ||
                readings.getTires().getRearRight()<32 || readings.getTires().getRearRight()>36 ||
                readings.getTires().getFrontLeft()<32 || readings.getTires().getFrontLeft()>36 ||
                readings.getTires().getFrontRight()<32 || readings.getTires().getFrontRight()>36){
            alertService.create(readings.getVin(), "LOW", readings.getTimestamp());
        }
        if(readings.isEngineCoolantLow() || readings.isCheckEngineLightOn()){
            alertService.create(readings.getVin(), "LOW", readings.getTimestamp());
        }
    }

    public List<VehicleReadings> findReadingsofVehicle(String id) {
        VehicleInfo vehicle = vehicleListService.findOne(id);
        return vehicleReadingsService.findReadingsofVehicle(vehicle.getVin());
    }

    public List<Alert> findAlertsofVehicle(String id) {
        VehicleInfo vehicle = vehicleListService.findOne(id);
        return alertService.findAlertsbyVin(vehicle.getVin());
    }

    public Map<VehicleInfo, Integer> countHighAlerts() {
        Map<VehicleInfo, Integer> highalertscount = new HashMap<VehicleInfo, Integer>();
        List<VehicleInfo> vehicleslist = vehicleListService.findAll().orElseThrow(()->new ResourceNotFoundException("No List of Vehicles exist"));
        vehicleslist.forEach(vehicle -> {
            int count = alertService.countHighAlertsbyVin(vehicle.getVin());
            highalertscount.put(vehicle,count);
        });
        LinkedHashMap<VehicleInfo, Integer> sortedAlertsCount = highalertscount.entrySet().stream()
                .sorted((a1, a2) -> a2.getValue()-a1.getValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap<VehicleInfo,Integer>::new));
        return sortedAlertsCount;
    }
}
