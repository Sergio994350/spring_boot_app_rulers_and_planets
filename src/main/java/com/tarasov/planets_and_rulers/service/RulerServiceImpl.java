package com.tarasov.planets_and_rulers.service;

import com.tarasov.planets_and_rulers.model.Ruler;
import com.tarasov.planets_and_rulers.repository.RulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RulerServiceImpl implements RulerService {

    @Autowired
    private RulerRepository rulerRepository;

    @Override
    public List<Ruler> getAllRulers() {
        return rulerRepository.findAll();
    }

    @Override
    public void saveRuler(Ruler ruler) {
        rulerRepository.save(ruler);
    }

    @Override
    public Ruler getRuler(long id) {

        Ruler ruler = null;
        Optional<Ruler> optional  = rulerRepository.findById(id);
        if (optional.isPresent()){
            ruler = optional.get();
        }
        return ruler;
    }

    @Override
    public void deleteRuler(long id) {
        rulerRepository.deleteById(id);
    }

    @Override
    public List<Ruler> findAllByName(String name) {

        List<Ruler> rulers = rulerRepository.findAllByName(name);
        return rulers;
    }


}
