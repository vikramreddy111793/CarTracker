package com.vbhoomidi.service.impl;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.repository.AlertRepository;
import com.vbhoomidi.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return repository.countHighAlertsbyVin(vin);
    }

    public List<Alert> findAlertsbyVin(String vin) {
        return repository.findAlertsbyVin(vin);
    }
}
