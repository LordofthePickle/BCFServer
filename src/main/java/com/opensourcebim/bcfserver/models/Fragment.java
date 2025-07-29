package com.opensourcebim.bcfserver.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Fragment {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foid;

    @NotNull
    @Column(nullable = false)
    private long expressValue;

    @NotNull
    @Column(nullable = false)
    private String ifcType;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String geometryPath; //for caching JSON files

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poid", nullable = false)
    private Project project;

    @NotNull
    @OneToMany(mappedBy = "fragment", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private List<IFCProperty> properties;

    //Methods
    public Fragment(){}
    public Fragment(long expressValue, String ifcType, String name, String geometryPath, Project project, List<IFCProperty> properties) {
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
    public String getGeometryPath() {
        return geometryPath;
    }
    public Project getProject() {
        return project;
    }
    public List<IFCProperty> getProperties() {
        return properties;
    }
}
