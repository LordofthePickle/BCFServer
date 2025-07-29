package com.opensourcebim.bcfserver.models;

import com.opensourcebim.bcfserver.models.enums.UserType;
import com.opensourcebim.bcfserver.models.logging.LogAction;
import com.opensourcebim.bcfserver.models.logging.UserLog;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uoid;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true)
    private String username;

    private String token;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Embedded
    @Column(nullable = false)
    private UserLog userlog;

    @NotNull
    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime creationTime;

    private LocalDateTime lastLoginTime;

    @ManyToOne
    @JoinColumn(name = "created_by_uoid")
    private User createdBy;

    @OneToMany(mappedBy = "createdBy")
    private List<User> createdUsers;

    @OneToMany(mappedBy = "createdBy")
    private List<Project> createdProjects;

    @ManyToMany
    @JoinTable(name = "user_project_access",
            joinColumns = @JoinColumn(name = "uoid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "poid", nullable = false))
    private List<Project> accessibleProjects;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    //Methods
    public User() {
    }

    public User(String username, String email, UserType userType, String token, User createdBy) {
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.creationTime = LocalDateTime.now();
        this.token = token;
        this.createdBy = createdBy;
        this.accessibleProjects = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.userlog = new UserLog();
        this.lastLoginTime = null;
        this.createdProjects = new ArrayList<>();
        this.createdUsers = new ArrayList<>();
    }

    public Long getUoid() {
        return uoid;
    }
    public String getUsername() {
        return username;
    }
    public boolean setUsername(String username) {
        this.username = username;
        return true;
    }
    public String getToken() {
        return token;
    }
    public boolean setToken(String token) {
        this.token = token;
        return true;
    }
    public UserType getUserType() {
        return userType;
    }
    public boolean setUserType(UserType userType) {
        this.userType = userType;
        return true;
    }
    /*
    public byte[] getPasswordHash(){
        TODO: stub
    }

   public byte[] getPasswordSalt(){
        TODO: stub
   }
   */
    public String getEmail() {
        return email;
    }
    public boolean setEmail(String email) {
        this.email = email;
        return true;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public User getCreatedBy() {
        return createdBy;
    }
    public UserLog getUserlog() {
        return userlog;
    }
    public boolean addLog(LogAction action){
       // return this.userlog.addLog(action); TODO: stub
        return true;
    }
    public List<Project> getAccessibleProjects() {
        return accessibleProjects;
    }
    public boolean addProject(Project project) {
        this.accessibleProjects.add(project);
        return true;
    }
    public boolean removeProject(Project project) {
        this.accessibleProjects.remove(project);
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
