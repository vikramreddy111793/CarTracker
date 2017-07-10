package com.vbhoomidi.repository.impl;

import com.vbhoomidi.entity.Alert;
import com.vbhoomidi.repository.AlertRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Repository
public class AlertRepositoryImpl implements AlertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Alert alert) {
        entityManager.persist(alert);
    }

    public List<Alert> findAlertsbyVin(String vin) {
        TypedQuery<Alert> query = entityManager.createNamedQuery("Alert.findAlertsbyVin", Alert.class);
        query.setParameter("givenVin", vin);
        List<Alert> list = query.getResultList();
        if(list != null){
            return list;
        }
        else{
            return null;
        }
    }

    public List<Alert> getHighAlertsbyVin(String vin) {
        TypedQuery<Alert> query = entityManager.createNamedQuery("Alert.countHighAlertsbyVin",Alert.class);
        query.setParameter("givenVin", vin);
        query.setParameter("givenPriority","HIGH");
        List<Alert> list = query.getResultList();
        if(list != null){
            return list;
        }
        else{
            return null;
        }
    }
}
