package com.daria.sprimg.mvc.repository;

import com.daria.sprimg.mvc.model.Address;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class AddressRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<Address> getAddresses(){
        Query query = entityManager.createQuery(" * from Address");
        return query.getResultList();
    }

    public List<String> getCountries(){
        Query query = entityManager.createQuery("select distinct country from Address");
        return query.getResultList();
    }

    public List<String> getUsersByCities(String city){
        Query query = entityManager.createQuery("from Address a where a.city=:ccity");
        query.setParameter("ccity", city);
        return query.getResultList();
    }
}
