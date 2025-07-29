package com.opensourcebim.bcfserver.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class IFCProperty {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propoid;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(length = 1024, nullable = false)
    private String value;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foid", nullable = false)
    private Fragment fragment;

    //Methods
    public IFCProperty(){}
    public IFCProperty(Fragment fragment, String name, String value) {
        this.fragment = fragment;
        this.name = name;
        this.value = value;
    }
    public Long getPropoid() {
        return propoid;
    }
    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }
}
