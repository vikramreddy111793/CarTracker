package com.vbhoomidi.repository;

import com.vbhoomidi.entity.Alert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Repository
public class AlertRepositoryImpl implements AlertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Alert> findAll() {
        return null;
    }

    public Alert findOne(String id) {
        return null;
    }

    public void create(Alert alert) {
        entityManager.persist(alert);
    }

    public List<Alert> findbyVin(String vin) {
        return null;
    }

    public int countHighAlertsbyVin(String vin) {
        TypedQuery<Alert> query = entityManager.createNamedQuery("Alert.countHighAlertsbyVin",Alert.class);
        query.setParameter("givenVin", vin);
        query.setParameter("givenPriority","HIGH");
        List<Alert> list = query.getResultList();
        int count=0;
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
