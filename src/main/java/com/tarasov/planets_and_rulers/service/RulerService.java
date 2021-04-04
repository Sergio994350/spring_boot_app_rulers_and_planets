package com.tarasov.planets_and_rulers.service;

import com.tarasov.planets_and_rulers.model.Ruler;

import java.util.List;

public interface RulerService {

    public List<Ruler> getAllRulers();

    public void saveRuler(Ruler ruler);

    public Ruler getRuler(long id);

    public void deleteRuler(long id);

    public List<Ruler> findAllByName(String name);


}
