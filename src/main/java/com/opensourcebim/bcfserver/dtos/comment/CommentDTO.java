package com.opensourcebim.bcfserver.dtos.comment;

import com.opensourcebim.bcfserver.models.Comment;

public class CommentDTO {
    private String commentText;
    private String createdBy;
    private Long foid;
    private String projectName;

    public CommentDTO() {}
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

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public void setFragment(Long foid) {
        this.foid = foid;
    }
    public void setProject(String projectName) {
        this.projectName = projectName;
    }
}
