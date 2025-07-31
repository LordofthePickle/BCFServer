package com.opensourcebim.bcfserver.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Comment {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coid;

    @NotNull
    @Column(length = 1024, nullable = false)
    private String text;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime creationTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uoid", nullable = false)
    private User createdBy;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poid", nullable = false)
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

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Project getProject() {
        return project;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
