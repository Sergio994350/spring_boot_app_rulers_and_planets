package com.tarasov.planets_and_rulers.repository;

import com.tarasov.planets_and_rulers.model.Ruler;
import com.tarasov.planets_and_rulers.model.RulerPlanet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RulerPlanetRepository extends JpaRepository<RulerPlanet, Long> {

//    List<RulerPlanet> findAllByOrderByPlanetId();

    List<RulerPlanet> findAllByPlanetIdIs(Long planet_id);

    RulerPlanet findByRulerId(Long ruler_id);

    RulerPlanet findByPlanetId(Long planet_id);



}
