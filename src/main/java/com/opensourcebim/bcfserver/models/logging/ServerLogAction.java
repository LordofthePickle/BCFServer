package com.opensourcebim.bcfserver.models.logging;

import com.opensourcebim.bcfserver.models.Comment;
import com.opensourcebim.bcfserver.models.Project;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.LogType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "server_log")
public class ServerLogAction {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loid;


    @NotNull
    private LocalDateTime time;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogType logType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poid", nullable = true)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uoid", nullable = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coid", nullable = true)
    private Comment comment;

    //Methods
    public Long getLoid() {
        return loid;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public LogType getLogType() {
        return logType;
    }

    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    public Comment getComment() {
        return comment;
    }
}
