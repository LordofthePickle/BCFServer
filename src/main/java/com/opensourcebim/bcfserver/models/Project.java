package com.opensourcebim.bcfserver.models;

import com.opensourcebim.bcfserver.models.enums.LengthMeasurePrefix;
import com.opensourcebim.bcfserver.models.enums.Schema;
import com.opensourcebim.bcfserver.models.logging.LogAction;
import com.opensourcebim.bcfserver.models.logging.ProjectLog;
import jakarta.persistence.*;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poid;

    private String name;

    private LocalDateTime creationTime;

    @OneToOne(mappedBy = "project")
    private User createdBy;

    private LengthMeasurePrefix measurementUnit;

    private Schema schema;

    @Embedded
    private ProjectLog projectlog;

    @Embedded
    private Path filePath;

    @ManyToMany(mappedBy = "accessibleProjects")
    private List<User> accessingUsers;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    //Methods
    public Project() {
        /*Default constructor*/
    }
    public Project(String name, User createdBy, LengthMeasurePrefix measurementUnit, Schema schema, Path filePath) {
        this.name = name;
        this.creationTime = LocalDateTime.now();
        this.createdBy = createdBy;
        this.measurementUnit = measurementUnit;
        this.schema = schema;
        this.filePath = filePath;
        this.accessingUsers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.projectlog = new ProjectLog();
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
    public Path getFilePath() {
        return filePath;
    }
    public ProjectLog getProjectlog() {
        return projectlog;
    }
    public boolean addLog(LogAction action){
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
