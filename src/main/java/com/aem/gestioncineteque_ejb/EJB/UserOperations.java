package com.aem.gestioncineteque_ejb.EJB;

import com.aem.gestioncineteque_ejb.entity.CD;
import org.hibernate.mapping.List;


public interface UserOperations {
    List<CD> browseCDs();
    boolean borrowCD(Long userId, Long cdId);
    boolean returnCD(Long userId, Long cdId);
}
