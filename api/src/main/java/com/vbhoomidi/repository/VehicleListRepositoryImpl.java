package com.vbhoomidi.repository;

import com.vbhoomidi.entity.VehicleInfo;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VehicleListRepositoryImpl implements VehicleListRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<VehicleInfo> findAll() {
        TypedQuery<VehicleInfo> query = entityManager.createNamedQuery("VehicleInfo.findAll", VehicleInfo.class);
        return query.getResultList();
    }

    public VehicleInfo findOne(String id) {
        return entityManager.find(VehicleInfo.class, id);
    }

    public VehicleInfo findbyVin(String vin) {
        TypedQuery<VehicleInfo> query = entityManager.createNamedQuery("VehicleInfo.findbyVin", VehicleInfo.class);
        query.setParameter("givenVin", vin);
        List<VehicleInfo> list = query.getResultList();
        if(list != null && list.size()==1){
            return list.get(0);
        }
        else{
            return null;
        }
    }

    public void update(VehicleInfo vehicle) {
        entityManager.merge(vehicle);
    }

    public void create(VehicleInfo vehicle) {
         entityManager.persist(vehicle);
    }
}
