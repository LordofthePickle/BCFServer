package com.opensourcebim.bcfserver.dtos;

import com.opensourcebim.bcfserver.models.Fragment;
import com.opensourcebim.bcfserver.models.Project;
import com.opensourcebim.bcfserver.models.User;

public class CommentDTO {
    private String commentText;
    private User createdBy;
    private Fragment fragment;
    private Project project;

    public String getCommentText() {
        return commentText;
    }

    public User getCreatedBy() {
        return createdBy;
    }
    public Fragment getFragment() {
        return fragment;
    }
    public Project getProject() {
        return project;
    }

}
