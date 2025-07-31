package com.opensourcebim.bcfserver.dtos;

import com.opensourcebim.bcfserver.models.Comment;
import com.opensourcebim.bcfserver.models.Fragment;
import com.opensourcebim.bcfserver.models.Project;
import com.opensourcebim.bcfserver.models.User;

public class CommentDTO {
    private String commentText;
    private String createdBy;
    private Long foid;
    private String projectName;

    public static CommentDTO from(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.commentText = comment.getText();
        dto.createdBy = comment.getCreatedBy().getUsername();
        if (comment.getFragment() != null) {
            dto.foid = comment.getFragment().getFoid();
        }
        else dto.foid = null;
        dto.projectName = comment.getProject().getName();
        return dto;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getCreatedBy() {
        return createdBy;
    }
    public Long getFragment() {
        return foid;
    }
    public String getProject() {
        return projectName;
    }

}
