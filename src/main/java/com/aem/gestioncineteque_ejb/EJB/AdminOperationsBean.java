package com.aem.gestioncineteque_ejb.EJB;

import com.aem.gestioncineteque_ejb.entity.CD;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@Stateful
public class AdminOperationsBean implements AdminOperations{

    @PersistenceContext
    private EntityManager entityManager;

    private List<CD> cdsToManage;

    public AdminOperationsBean() {
        cdsToManage = new ArrayList<>();
    }

    @Override
    public void addCD(CD cd) {
        cdsToManage.add(cd);
    }

    @Override
    public void removeCD(Long cdId) {
        CD cd = entityManager.find(CD.class, cdId);
        if (cd != null) {
            entityManager.remove(cd);
        }
    }

    @Override
    public void saveChanges() {
        for (CD cd : cdsToManage) {
            entityManager.persist(cd);
        }
        cdsToManage.clear();  // Reset after saving
    }

    @Override
    public List<CD> getCDsToManage() {
        return cdsToManage;
    }
}
