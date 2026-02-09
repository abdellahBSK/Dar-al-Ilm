package com.fstt.lms.service;

import com.fstt.lms.model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "libraryPU")
    private EntityManager em;

    public User authenticate(String username, String password) {
        try {
            return em
                    .createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                            User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
