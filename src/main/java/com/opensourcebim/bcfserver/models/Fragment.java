package com.opensourcebim.bcfserver.models;

import jakarta.persistence.*;

import java.nio.file.Path;
import java.util.List;

@Entity
public class Fragment {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foid;

    private long expressValue;

    private String ifcType;

    private String name;

    @Embedded
    private Path geometryPath; //for caching JSON files

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poid")
    private Project project;

    @OneToMany(mappedBy = "fragment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IFCProperty> properties;

    //Methods
    public Fragment(){}
    public Fragment(long expressValue, String ifcType, String name, Path geometryPath, Project project, List<IFCProperty> properties) {
        this.expressValue = expressValue;
        this.ifcType = ifcType;
        this.name = name;
        this.geometryPath = geometryPath;
        this.project = project;
        this.properties = properties;
    }

    public Long getFoid() {
        return foid;
    }
    public long getExpressValue() {
        return expressValue;
    }
    public String getIfcType() {
        return ifcType;
    }
    public String getName() {
        return name;
    }
    public Path getGeometryPath() {
        return geometryPath;
    }
    public Project getProject() {
        return project;
    }
    public List<IFCProperty> getProperties() {
        return properties;
    }
}
