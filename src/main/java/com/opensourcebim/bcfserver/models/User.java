package com.opensourcebim.bcfserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opensourcebim.bcfserver.models.enums.UserType;
import com.opensourcebim.bcfserver.models.enums.LogType;
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

    @NotNull
    @Size(min = 8, max = 100)
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    private String token;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

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

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Comment> comments;

    //Methods
    public User() {
    }

    public User(String username, String email, String password, UserType userType){
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.creationTime = LocalDateTime.now();
        this.token = null;
        this.createdBy = this;
        this.accessibleProjects = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public User(String username, String email, String password, UserType userType, String token, User createdBy) {
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.creationTime = LocalDateTime.now();
        this.token = token;
        this.createdBy = createdBy;
        this.accessibleProjects = new ArrayList<>();
        this.comments = new ArrayList<>();
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
    public void setUsername(String username) {
        this.username = username;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public List<User> getCreatedUsers() {
        return createdUsers;
    }
    public void addCreatedUser(User user) {
        createdUsers.add(user);
    }
    public void addLog(LogType action){
       // return this.userlog.addLog(action); TODO: stub
    }
    public List<Project> getAccessibleProjects() {
        return accessibleProjects;
    }
    public void addProject(Project project) {
        this.accessibleProjects.add(project);
    }
    public void removeProject(Project project) {
        accessibleProjects.remove(project);
    }
    public List<Project> getCreatedProjects() {
        return createdProjects;
    }
    public void addCreatedProject(Project project) {
        createdProjects.add(project);
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public String toString(){
        return username;
    }
}
