package com.aem.gestioncineteque_ejb.EJB;

import com.aem.gestioncineteque_ejb.entity.CD;

import java.util.List;

public interface AdminOperations {
    void addCD(CD cd);
    void removeCD(Long cdId);
    void saveChanges();
    List<CD> getCDsToManage();
}
