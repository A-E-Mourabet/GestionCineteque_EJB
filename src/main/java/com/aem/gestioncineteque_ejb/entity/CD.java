package com.aem.gestioncineteque_ejb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CD {

    @Id
    private Long id;
    private String title;
    private String artist;
    private boolean available;
    private Long borrowedBy;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Long getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Long borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
}

