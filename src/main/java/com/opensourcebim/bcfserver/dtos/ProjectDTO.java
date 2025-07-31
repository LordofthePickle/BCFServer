package com.opensourcebim.bcfserver.dtos;

import com.opensourcebim.bcfserver.models.User;
import com.opensourcebim.bcfserver.models.enums.LengthMeasurePrefix;
import com.opensourcebim.bcfserver.models.enums.Schema;

public class ProjectDTO {
    private String name;
    private User createdBy;
    private LengthMeasurePrefix lengthMeasurePrefix;
    private Schema schema;
    private String filePath;

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
}
