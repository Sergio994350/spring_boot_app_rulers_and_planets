package com.tarasov.planets_and_rulers;

import com.tarasov.planets_and_rulers.controller.GalacticController;
import com.tarasov.planets_and_rulers.model.Planet;
import com.tarasov.planets_and_rulers.model.Ruler;
import com.tarasov.planets_and_rulers.model.RulerPlanet;
import com.tarasov.planets_and_rulers.repository.PlanetRepository;
import com.tarasov.planets_and_rulers.repository.RulerPlanetRepository;
import com.tarasov.planets_and_rulers.repository.RulerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
public class GalacticControllerTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RulerRepository rulerRepository;

    @Autowired
    private RulerPlanetRepository rulerPlanetRepository;

    // сделать тесты:

    // showAllRulers
    // showAllPlanets

    // showYoungRulers
    // showRulersNoPlanet

    // rulersAddPostMap
    // planetsAddPostMap

    // rulerDeletePostMap
    // planetDeletePostMap


    @Test
    void testShowAllRulers() throws Exception {
        this.entityManager.persist(new Ruler("Petr", 20));
        this.entityManager.persist(new Ruler("Karl", 21));
        this.entityManager.persist(new Ruler("Henry", 22));
        this.entityManager.persist(new Ruler("Ivan", 23));

        List<Ruler> rulerList = this.rulerRepository.findAll();
        assertThat(rulerList.get(0).getName()).isEqualTo("Petr");
        assertThat(rulerList.get(2).getAge()).isEqualTo(22);
        assertThat(rulerList.get(1).getAge()).isEqualTo(21);
    }

    @Test
    void testShowAllPlanets() throws Exception {
        this.entityManager.persist(new Planet("Mercury", 1));
        this.entityManager.persist(new Planet("Mars", 2));
        this.entityManager.persist(new Planet("Venus", 15));
        this.entityManager.persist(new Planet("Uranus", 12));

        List<Planet> planetList = this.planetRepository.findAll();
        assertThat(planetList.get(1).getName()).isEqualTo("Mars");
        assertThat(planetList.get(2).getRulerId()).isEqualTo(15);
        assertThat(planetList.get(3).getRulerId()).isEqualTo(12);
    }

    @Test
    void testShowYoungRulers() throws Exception {
        this.entityManager.persist(new Ruler("Petr1", 20));
        this.entityManager.persist(new Ruler("Karl1", 21));
        this.entityManager.persist(new Ruler("Henry", 22));
        this.entityManager.persist(new Ruler("Ivan1", 23));
        this.entityManager.persist(new Ruler("Ivan2", 30));
        this.entityManager.persist(new Ruler("Ivan3", 18));
        this.entityManager.persist(new Ruler("Petr2", 60));
        this.entityManager.persist(new Ruler("Petr3", 70));
        this.entityManager.persist(new Ruler("Karl2", 55));
        this.entityManager.persist(new Ruler("Karl3", 65));
        this.entityManager.persist(new Ruler("Karl4", 67));
        this.entityManager.persist(new Ruler("Karl5", 68));


        List<Ruler> rulerList = this.rulerRepository.findTop10ByOrderByAgeAsc();
        assertThat(rulerList.get(1).getName()).isEqualTo("Petr1");
        assertThat(rulerList.get(3).getAge()).isEqualTo(22);
        assertThat(rulerList.get(4).getName()).isEqualTo("Ivan1");
        assertThat(rulerList.get(5).getAge()).isEqualTo(30);
    }

    @Test
    void testShowRulersNoPlanets() throws Exception {
        long zero_id = 0;
        this.entityManager.persist(new RulerPlanet(1, 1));
        this.entityManager.persist(new RulerPlanet(2, 2));
        this.entityManager.persist(new RulerPlanet(3, 0));
        this.entityManager.persist(new RulerPlanet(4, 12));
        this.entityManager.persist(new RulerPlanet(5, 0));
        this.entityManager.persist(new RulerPlanet(6, 0));

        List<RulerPlanet> rulerNoPlanetList = this.rulerPlanetRepository
                .findAllByPlanetIdIs(zero_id);
        assertThat(rulerNoPlanetList.get(0).getRulerId()).isEqualTo(3);
        assertThat(rulerNoPlanetList.get(1).getRulerId()).isEqualTo(5);
        assertThat(rulerNoPlanetList.get(2).getRulerId()).isEqualTo(6);
    }

    @Test
    void testAddNewRuler() throws Exception {
        this.entityManager.persist(new Ruler("Petr1", 30));

        Ruler ruler = this.rulerRepository.findByName("Petr1");
        assertThat(ruler.getName()).isEqualTo("Petr1");
        assertThat(ruler.getAge()).isEqualTo(30);
    }

    @Test
    void testAddNewPlanet() throws Exception {
        this.entityManager.persist(new Planet("Alfa", 30));

        Planet planet = this.planetRepository.findByName("Alfa");
        assertThat(planet.getName()).isEqualTo("Alfa");
        assertThat(planet.getRulerId()).isEqualTo(30);
    }

    @Test
    void testDeleteRuler() throws Exception {
        this.entityManager.persist(new Ruler("Petr1", 30));
        this.entityManager.persist(new Ruler("Petr2", 31));
        this.entityManager.persist(new Ruler("Petr3", 32));

        Ruler ruler = this.rulerRepository.findByName("Petr2");
        rulerRepository.delete(ruler);

        List<Ruler> rulerList = this.rulerRepository.findAll();

        assertThat(rulerList.get(0).getName()).isEqualTo("Petr1");
        assertThat(rulerList.get(1).getName()).isEqualTo("Petr3");
    }

    @Test
    void testDeletePlanet() throws Exception {
        this.entityManager.persist(new Planet("Beta", 12));
        this.entityManager.persist(new Planet("Gamma", 13));
        this.entityManager.persist(new Planet("Omega", 14));

        Planet planet = this.planetRepository.findByName("Beta");
        planetRepository.delete(planet);

        List<Planet> planetList = this.planetRepository.findAll();

        assertThat(planetList.get(0).getName()).isEqualTo("Gamma");
        assertThat(planetList.get(1).getRulerId()).isEqualTo(14);
    }








}
