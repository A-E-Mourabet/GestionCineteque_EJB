package com.aem.gestioncineteque_ejb.EJB;

import com.aem.gestioncineteque_ejb.entity.CD;
import jakarta.ejb.Stateless;
import org.hibernate.mapping.List;


@Stateless
public class UserOperationsBean implements  UserOperations{
    @Override
    public List<CD> browseCDs() {
        // Query the database to return a list of available CDs
        // Placeholder for database interaction
        return entityManager.createQuery("SELECT cd FROM CD cd", CD.class).getResultList();
    }

    @Override
    public boolean borrowCD(Long userId, Long cdId) {
        // Logic to borrow a CD, e.g., check if CD is available, mark it as borrowed by userId
        CD cd = entityManager.find(CD.class, cdId);
        if (cd.isAvailable()) {
            cd.setBorrowedBy(userId);
            cd.setAvailable(false);
            entityManager.merge(cd);
            return true;
        }
        return false;
    }

    // Return a CD
    @Override
    public boolean returnCD(Long userId, Long cdId) {
        CD cd = entityManager.find(CD.class, cdId);
        if (cd != null && cd.getBorrowedBy().equals(userId)) {
            cd.setAvailable(true);
            cd.setBorrowedBy(null);
            entityManager.merge(cd);
            return true;
        }
        return false;
    }

}
