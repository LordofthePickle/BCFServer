package com.opensourcebim.bcfserver.models;

import jakarta.persistence.*;

@Entity
public class IFCProperty {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propoid;

    private String name;

    @Column(length = 1024)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foid")
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
