package com.vbhoomidi.repository;

import com.vbhoomidi.entity.Tire;
import com.vbhoomidi.entity.VehicleReadings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by vikramreddy on 7/9/2017.
 */
@Repository
public class VehiclesReadingsRepositoryImpl implements VehicleReadingsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void readingsCreate(VehicleReadings readings) {
        entityManager.persist(readings);
    }

    public Tire tireCreate(Tire tire) {
        entityManager.persist(tire);
        return tire;
    }

    public List<VehicleReadings> findReadingsbyVin(String vin) {
        TypedQuery<VehicleReadings> query = entityManager.createNamedQuery("VehicleReadings.findbyVin", VehicleReadings.class);
        query.setParameter("givenVin", vin);
        List<VehicleReadings> list = query.getResultList();
        if(list != null){
            return list;
        }
        else{
            return null;
        }
    }

}
