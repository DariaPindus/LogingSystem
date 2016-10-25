package com.daria.sprimg.mvc.repository;

import com.daria.sprimg.mvc.model.DayTemperature;
import com.daria.sprimg.mvc.model.Temperature;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TemperatureRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<Temperature> getDayTemperature(){
        Query query = entityManager.createQuery(" * from DayTemperature");
        return query.getResultList();
    }

    @Transactional
    public void addDayTemperature(DayTemperature dayTemperature) {
        entityManager.persist(dayTemperature);
        entityManager.flush();
    }

}
