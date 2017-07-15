package com.vbhoomidi.repository.impl;

import com.vbhoomidi.entity.VehicleInfo;
import com.vbhoomidi.repository.VehicleListRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleListRepositoryImpl implements VehicleListRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<VehicleInfo> findAll() {
        TypedQuery<VehicleInfo> query = entityManager.createNamedQuery("VehicleInfo.findAll", VehicleInfo.class);
        List<VehicleInfo> list = query.getResultList();
        if(!list.isEmpty()){
            return list;
        }
        else{
            return null;
        }
    }

    public Optional<VehicleInfo> findOne(String id) {
        return Optional.ofNullable(entityManager.find(VehicleInfo.class, id));
    }


    @CachePut(value = "vehiclesbyID", key = "#vehicle.id")
    public VehicleInfo update(VehicleInfo vehicle) {
        entityManager.merge(vehicle);
        return vehicle;
    }

    public void create(VehicleInfo vehicle) {
         entityManager.persist(vehicle);
    }
}
