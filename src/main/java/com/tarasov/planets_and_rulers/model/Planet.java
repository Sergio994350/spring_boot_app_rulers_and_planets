package com.tarasov.planets_and_rulers.model;

import javax.persistence.*;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Column(name = "ruler_id")
    private long rulerId;

    public Planet() {
    }

    public Planet(String name, long rulerId) {
        this.name = name;
        this.rulerId = rulerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRulerId() {
        return rulerId;
    }

    public void setRulerId(long rulerId) {
        this.rulerId = rulerId;
    }

}
