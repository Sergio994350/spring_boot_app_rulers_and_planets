package com.tarasov.planets_and_rulers.controller;

import com.tarasov.planets_and_rulers.model.Planet;
import com.tarasov.planets_and_rulers.model.Ruler;
import com.tarasov.planets_and_rulers.model.RulerPlanet;
import com.tarasov.planets_and_rulers.repository.PlanetRepository;
import com.tarasov.planets_and_rulers.repository.RulerPlanetRepository;
import com.tarasov.planets_and_rulers.repository.RulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class GalacticController {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RulerRepository rulerRepository;

    @Autowired
    private RulerPlanetRepository rulerPlanetRepository;

    // вывод всех правителей --- V
    @GetMapping("/rulers")
    public String showAllRulers(Model model) {
        Iterable<Ruler> rulers = rulerRepository.findAll();
        model.addAttribute("rulers", rulers);
        return "all-rulers";
    }

    // вывод всех планет --- V
    @GetMapping("/planets")
    public String showAllPlanets(Model model) {
        Iterable<Planet> planets = planetRepository.findAll();
        model.addAttribute("planets", planets);
        return "all-planets";
    }

    // вывод 10 самых молодых правителей --- V
    // select name, age from planets_db.ruler order by age asc limit 10;
    @GetMapping("/rulers/young")
    public String showYoungRulers(Model model) {

        Iterable<Ruler> youngRulers = rulerRepository.findTop10ByOrderByAgeAsc();

        model.addAttribute("youngRulers", youngRulers);
        return "young-rulers";
    }

    // вывод всех правителей без планет --- V
    @GetMapping("/no-planet-rulers")
    public String showRulersNoPlanet(Model model) {

        long planet_id = 0;
        Iterable<RulerPlanet> rulersNoPlanet = rulerPlanetRepository
                .findAllByPlanetIdIs(planet_id);

        model.addAttribute("rulersNoPlanet", rulersNoPlanet);
        return "no-planet-rulers";
    }


    // добавление нового правителя - переход на страничку заполнения формы --- V
    @GetMapping("/rulers/add")
    public String rulersAdd(Model model) {
        return "rulers-add";
    }

    // добавление нового правителя - получение данных из формы и запись в БД --- V
    @PostMapping(value = "/rulers/add")
    public String rulersAddPostMap(@RequestParam String name,
                                   @RequestParam int age,
                                   Model model) {

        Ruler ruler = new Ruler(name, age);
        rulerRepository.save(ruler);

        RulerPlanet rulerPlanet = new RulerPlanet(ruler.getId(), 0);
        rulerPlanetRepository.save(rulerPlanet);

        return "redirect:/";
    }


    // добавление новой планеты - переход на страничку заполнения формы --- V
    @GetMapping("/planets/add")
    public String planetsAdd(Model model) {
        return "planets-add";
    }

    // добавление новой планеты - получение данных из формы и запись в БД
    @PostMapping(value = "/planets/add")
    public String planetsAddPostMap(@RequestParam String name,
                                    Model model) {

        Planet planet = new Planet(name, 0);
        planetRepository.save(planet);

//        RulerPlanet rulerPlanet = new RulerPlanet(0, planet.getId());
//        rulerPlanetRepository.save(rulerPlanet);

        return "redirect:/";
    }


    // назначить правителя управлять планетой - переход на страничку заполнения формы - V
    @GetMapping("/order")
    public String orderRulerToPlanet(Model model) {
        return "order";
    }


    // назначить правителя управлять планетой -
    // получение данных из формы и запись в БД  --- V
    @PostMapping(value = "/order")
    public String orderRulerToPlanetPost(@RequestParam long ruler_id,
                                         @RequestParam long planet_id,
                                         Model model) {

        Ruler ruler = rulerRepository.findById(ruler_id).orElseThrow();
        Planet planet = planetRepository.findById(planet_id).orElseThrow();
        RulerPlanet rulerPlanet = rulerPlanetRepository.findByRulerId(ruler_id);


        try {
            // если у правителя нет планеты
            if (rulerPlanet.getPlanetId() == 0) {

                planet.setRulerId(ruler_id);
                planetRepository.save(planet);

                rulerPlanet.setPlanetId(planet_id);
                rulerPlanetRepository.save(rulerPlanet);
            }

            // если у планеты нет правителя
            if (planet.getRulerId() == 0) {

                planet.setRulerId(ruler_id);
                planetRepository.save(planet);

                rulerPlanet.setRulerId(ruler_id);
                rulerPlanetRepository.save(rulerPlanet);
            }


            // если у планеты есть правитель
            if (rulerPlanet.getPlanetId() != 0) {

                RulerPlanet rulerPlanet1 = rulerPlanetRepository.findByPlanetId(planet_id);
                RulerPlanet rulerPlanet2 = rulerPlanetRepository.findByRulerId(ruler_id);

                rulerPlanet1.setRulerId(rulerPlanet2.getRulerId());
                rulerPlanet2.setPlanetId(rulerPlanet1.getPlanetId());

                planet.setRulerId(ruler.getId());

                rulerPlanetRepository.save(rulerPlanet1);
                rulerPlanetRepository.save(rulerPlanet2);
                planetRepository.save(planet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/";
    }


    // удаление правителя --- V
    @PostMapping("/rulers/{id}/remove")
    public String rulerDeletePostMap(@PathVariable(value = "id") long id, Model model) {

        Ruler ruler = rulerRepository.findById(id).orElseThrow();
        RulerPlanet rulerPlanet = rulerPlanetRepository.findByRulerId(id);

        rulerPlanet.setRulerId(0);
        rulerRepository.delete(ruler);
        return "redirect:/";
    }

    // удаление планеты --- V
    @PostMapping("/planets/{id}/remove")
    public String planetDeletePostMap(@PathVariable(value = "id") long id, Model model) {

        Planet planet = planetRepository.findById(id).orElseThrow();
        RulerPlanet rulerPlanet = rulerPlanetRepository.findByPlanetId(id);

        rulerPlanet.setPlanetId(0);
        planetRepository.delete(planet);
        return "redirect:/";
    }


}