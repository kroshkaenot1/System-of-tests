package com.rgr.system_of_tests.repo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private Boolean isPrivate, isLimited;
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setLimited(Boolean limited) {
        isLimited = limited;
    }

    public Boolean getLimited() {
        return isLimited;
    }

    public Test(String title, String description, Boolean isPrivate) {
        this.title = title;
        this.description = description;
        this.date = LocalDateTime.now();
        this.isPrivate = isPrivate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Test() {
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
