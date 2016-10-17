package com.daria.sprimg.mvc.repository;

import com.daria.sprimg.mvc.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public List<User> getUsers(){
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Transactional
    public User getUserByLogin(String login) {
        Query query = entityManager.createQuery("from User u where u.userName=:login");
        query.setParameter("login", login);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Transactional
    public List<User> getUsersByCity(String city) {
        System.out.println("got in user repository");
        Query query = entityManager.createQuery("select u from User u join u.address a where a.city=:ccity", User.class);
        query.setParameter("ccity", city);
        return query.getResultList();
    }

    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    public void updateValues(String newStr, String name) {
        Query query = entityManager.createQuery("UPDATE User u SET u.tkn = :userTkn WHERE u.userName = :name");
        query.setParameter("userTkn", newStr);
        query.setParameter("name", name);
        query.executeUpdate();
    }
}
