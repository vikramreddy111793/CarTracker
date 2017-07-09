package com.vbhoomidi.repository;

import com.vbhoomidi.entity.Tire;
import com.vbhoomidi.entity.VehicleReadings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Repository
public class VehiclesReadingsRepositoryImpl implements VehicleReadingsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<VehicleReadings> findAll() {
        return null;
    }

    public VehicleReadings findOne(String id) {
        return null;
    }

    public void readingsCreate(VehicleReadings readings) {
        entityManager.persist(readings);
    }

    public Tire tireCreate(Tire tire) {
        entityManager.persist(tire);
        return tire;
    }

    public VehicleReadings findbyVin(String vin) {
        return null;
    }

    public void update(VehicleReadings vehicle) {

    }
}
