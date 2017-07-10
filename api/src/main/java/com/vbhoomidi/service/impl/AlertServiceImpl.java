package com.vbhoomidi.service.impl;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.exception.ResourceNotFoundException;
import com.vbhoomidi.repository.AlertRepository;
import com.vbhoomidi.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void create(String vin, String priority, Date timeStamp) {
        Alert alert = new Alert();
        alert.setPriority(priority);
        alert.setTimeStamp(timeStamp);
        alert.setVin(vin);
        repository.create(alert);
    }

    public int countHighAlertsbyVin(String vin) {
        List<Alert> list = repository.getHighAlertsbyVin(vin);
        int count=0;
        if(list == null){
            return count;
        }
        else{
            for(int i=0; i<list.size(); i++){
                Calendar previous = Calendar.getInstance();
                Calendar current = Calendar.getInstance();
                previous.setTime((list.get(i)).getTimeStamp());
                long difference = current.getTimeInMillis()-previous.getTimeInMillis();
                if(difference<=2*60*60*1000){
                    count++;
                }
            }
            return count;
        }
    }

    public List<Alert> findAlertsbyVin(String vin) {
        List<Alert> list = repository.findAlertsbyVin(vin);
        if(list == null || list.size()==0){
            throw new ResourceNotFoundException("This vehicle with vin-"+vin+" doesn't have any Alerts");
        }
        return list;
    }
}