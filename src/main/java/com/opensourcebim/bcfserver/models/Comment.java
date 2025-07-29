package com.opensourcebim.bcfserver.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coid;

    private String text;

    private LocalDateTime creationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uoid")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poid")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foid", nullable = true)
    private Fragment fragment;

    //Methods
    public Comment(){}
    public Comment(String text, User createdBy, Project project) {
        this.text = text;
        this.creationTime = LocalDateTime.now();
        this.createdBy = createdBy;
        this.project = project;
        this.fragment = null;
    }
    public Comment(String text, User createdBy, Project project, Fragment fragment) {
        this.text = text;
        this.creationTime = LocalDateTime.now();
        this.createdBy = createdBy;
        this.project = project;
        this.fragment = fragment;
    }
    public long getCoid() {
        return coid;
    }
    public String getText() {
        return text;
    }
    public boolean setText(String text) {
        this.text = text;
        return true;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    public User getCreatedBy() {
        return createdBy;
    }
    public Fragment getFragment() {
        return fragment;
    }
}
