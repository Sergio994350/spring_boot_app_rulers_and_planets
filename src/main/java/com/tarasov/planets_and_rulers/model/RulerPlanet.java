package com.tarasov.planets_and_rulers.model;

import javax.persistence.*;

@Entity
@Table(name = "ruler_of_planet")
public class RulerPlanet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ruler_id")
    private long rulerId;

    @Column(name = "planet_id")
    private long planetId;

    public RulerPlanet() {
    }

    public RulerPlanet(long rulerId, long planetId) {
        this.rulerId = rulerId;
        this.planetId = planetId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRulerId() {
        return rulerId;
    }

    public void setRulerId(long rulerId) {
        this.rulerId = rulerId;
    }

    public long getPlanetId() {
        return planetId;
    }

    public void setPlanetId(long planetId) {
        this.planetId = planetId;
    }


}
