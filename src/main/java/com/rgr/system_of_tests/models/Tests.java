package com.rgr.system_of_tests.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Tests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title,description;
    private LocalDateTime date;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Tests(String title, String description) {
        this.title = title;
        this.description = description;
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Tests() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
