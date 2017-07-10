package com.vbhoomidi.repository;

import com.vbhoomidi.entity.Alert;

import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
public interface AlertRepository {
    void create(Alert alert);
    List<Alert> findAlertsbyVin(String vin);
    int countHighAlertsbyVin(String vin);
}
