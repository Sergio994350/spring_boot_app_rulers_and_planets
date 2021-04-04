package com.tarasov.planets_and_rulers.repository;

import com.tarasov.planets_and_rulers.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    Planet findByName(String name);

}
