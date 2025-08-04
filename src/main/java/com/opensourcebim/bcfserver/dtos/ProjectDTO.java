package com.opensourcebim.bcfserver.dtos;

import com.opensourcebim.bcfserver.models.Project;
import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.LengthMeasurePrefix;
import com.opensourcebim.bcfserver.models.enums.Schema;

public class ProjectDTO {
    private String name;
    private User createdBy;
    private LengthMeasurePrefix lengthMeasurePrefix;
    private Schema schema;
    private String filePath;

    public ProjectDTO() {}

    public static ProjectDTO from(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.name = project.getName();
        dto.createdBy = project.getCreatedBy();
        dto.lengthMeasurePrefix = project.getLengthMeasurePrefix();
        dto.schema = project.getSchema();
        dto.filePath = project.getFilePath();
        return dto;
    }


    public String getName() {
        return name;
    }
    public User getCreatedBy() {
        return createdBy;
    }
    public LengthMeasurePrefix getLengthMeasurePrefix() {
        return lengthMeasurePrefix;
    }
    public Schema getSchema() {
        return schema;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    public void setLengthMeasurePrefix(LengthMeasurePrefix lengthMeasurePrefix) {
        this.lengthMeasurePrefix = lengthMeasurePrefix;
    }
    public void setSchema(Schema schema) {
        this.schema = schema;
    }
}
