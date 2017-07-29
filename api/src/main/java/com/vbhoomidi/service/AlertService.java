package com.vbhoomidi.service;

import com.vbhoomidi.entity.Alert;

import java.util.Date;
import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
public interface AlertService {
    List<Alert> create(String vin, String priority, String description, Date timeStamp);
    int countHighAlertsbyVin(String vin);
    List<Alert> findAlertsbyVin(String vin);
}
