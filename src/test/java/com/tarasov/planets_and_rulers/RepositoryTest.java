package com.tarasov.planets_and_rulers;

import com.tarasov.planets_and_rulers.model.Planet;
import com.tarasov.planets_and_rulers.model.Ruler;
import com.tarasov.planets_and_rulers.model.RulerPlanet;
import com.tarasov.planets_and_rulers.repository.PlanetRepository;
import com.tarasov.planets_and_rulers.repository.RulerPlanetRepository;
import com.tarasov.planets_and_rulers.repository.RulerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
public class RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RulerRepository rulerRepository;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RulerPlanetRepository rulerPlanetRepository;

    @Test
    void testRuler() throws Exception {
        this.entityManager.persist(new Ruler("Henry VIII", 50));
        Ruler ruler = this.rulerRepository.findByName("Henry VIII");
        assertThat(ruler.getName()).isEqualTo("Henry VIII");
        assertThat(ruler.getAge()).isEqualTo(50);
    }

    @Test
    void testPlanet() throws Exception {
        this.entityManager.persist(new Planet("Mars", 1));
        Planet planet = this.planetRepository.findByName("Mars");
        assertThat(planet.getName()).isEqualTo("Mars");
        assertThat(planet.getRulerId()).isEqualTo(1);
    }

    @Test
    void testRulerPlanet() throws Exception {
        long testRulerId = 1;
        long testPlanetId = 1;

        this.entityManager.persist(new RulerPlanet(testRulerId, testPlanetId));
        RulerPlanet rulerPlanet = this.rulerPlanetRepository.findByRulerId(testRulerId);
        assertThat(rulerPlanet.getPlanetId()).isEqualTo(testPlanetId);
        assertThat(rulerPlanet.getRulerId()).isEqualTo(testRulerId);
    }

}

