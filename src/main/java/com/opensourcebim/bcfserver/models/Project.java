package com.opensourcebim.bcfserver.models;

import com.opensourcebim.bcfserver.models.enums.LengthMeasurePrefix;
import com.opensourcebim.bcfserver.models.enums.Schema;
import com.opensourcebim.bcfserver.models.enums.LogType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poid;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "created_by_uoid", nullable = false)
    private User createdBy;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LengthMeasurePrefix measurementUnit;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Schema schema;

    @Column(nullable = false)
    private String filePath;

    @ManyToMany(mappedBy = "accessibleProjects")
    private List<User> accessingUsers;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    //Methods
    public Project() {
        /*Default constructor*/
    }
    public Project(String name, User createdBy, LengthMeasurePrefix measurementUnit, Schema schema, String filePath) {
        this.name = name;
        this.creationTime = LocalDateTime.now();
        this.createdBy = createdBy;
        this.measurementUnit = measurementUnit;
        this.schema = schema;
        this.filePath = filePath;
        this.accessingUsers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Long getPoid() {
        return poid;
    }
    public String getName() {
        return name;
    }
    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    public User getCreatedBy() {
        return createdBy;
    }
    public LengthMeasurePrefix getMeasurementUnit() {
        return measurementUnit;
    }
    public Schema getSchema() {
        return schema;
    }
    public String getFilePath() {
        return filePath;
    }
    public boolean addLog(LogType action){
        //return projectlog.addLog(action); TODO: stub
        return true;
    }
    public List<User> getAccessingUsers() {
        return accessingUsers;
    }
    public boolean addUser(User user){
        this.accessingUsers.add(user);
        return true;
    }
    public boolean removeUser(User user){
        this.accessingUsers.remove(user);
        return true;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public boolean addComment(Comment comment) {
        this.comments.add(comment);
        return true;
    }
    public boolean removeComment(Comment comment) {
        this.comments.remove(comment);
        return true;
    }
}
