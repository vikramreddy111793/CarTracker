package com.vbhoomidi.service.impl;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.exception.ResourceNotFoundException;
import com.vbhoomidi.repository.AlertRepository;
import com.vbhoomidi.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository repository;

    @CachePut(value = "alertsbyVIN", key = "#vin")
    public List<Alert> create(String vin, String priority, String description, Date timeStamp) {
        Alert alert = new Alert();
        alert.setPriority(priority);
        alert.setDescription(description);
        alert.setTimeStamp(timeStamp);
        alert.setVin(vin);
        repository.create(alert);
        return repository.findAlertsbyVin(vin);
    }

    public int countHighAlertsbyVin(String vin) {
        List<Alert> alertslist = repository.getHighAlertsbyVin(vin);
        int count=0;
        if(alertslist != null){
            for(Alert a : alertslist){

                    Calendar previous = Calendar.getInstance();
                    Calendar current = Calendar.getInstance();
                    previous.setTime(a.getTimeStamp());
                    long difference = current.getTimeInMillis()-previous.getTimeInMillis();
                    if(difference<=2*60*60*1000){
                        count++;
                    }

            }
        }
        return count;
    }

    @Cacheable(value = "alertsbyVIN", key = "#vin")
    public List<Alert> findAlertsbyVin(String vin) {
        List<Alert> list = repository.findAlertsbyVin(vin);
        if(list == null){
            throw new ResourceNotFoundException("This vehicle with vin-"+vin+" doesn't have any Alerts");
        }
        return list;
    }
}