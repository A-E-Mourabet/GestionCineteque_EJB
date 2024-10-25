package com.aem.gestioncineteque_ejb.web_client;

import com.aem.gestioncineteque_ejb.EJB.UserOperations;
import com.aem.gestioncineteque_ejb.entity.CD;
import jakarta.ejb.EJB;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class CDManagedBean {

    @EJB
    private UserOperations userOperations;

    private Long userId;
    private Long selectedCdId;
    private List<CD> availableCDs;
    private String message;

    // Initialize by fetching available CDs
    public List<CD> getAvailableCDs() {
        if (availableCDs == null) {
            availableCDs = userOperations.browseCDs();
        }
        return availableCDs;
    }

    // Borrow CD
    public String borrowCD(Long cdId) {
        boolean result = userOperations.borrowCD(userId, cdId);
        if (result) {
            message = "CD borrowed successfully!";
        } else {
            message = "CD is not available.";
        }
        return "cdList.xhtml";  // Redirect to the list of CDs after borrowing
    }

    // Check if CD is borrowed
    public boolean isCdBorrowed(CD cd) {
        return !cd.isAvailable();
    }

    // Return CD
    public String returnCD(Long cdId) {
        boolean result = userOperations.returnCD(userId, cdId);
        if (result) {
            message = "CD returned successfully!";
        } else {
            message = "Error returning CD.";
        }
        return "cdList.xhtml";  // Redirect to the list of CDs after returning
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSelectedCdId() {
        return selectedCdId;
    }

    public void setSelectedCdId(Long selectedCdId) {
        this.selectedCdId = selectedCdId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

