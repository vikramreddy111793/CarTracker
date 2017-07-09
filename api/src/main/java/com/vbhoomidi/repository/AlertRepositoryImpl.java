package com.vbhoomidi.repository;

import com.vbhoomidi.entity.Alert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
