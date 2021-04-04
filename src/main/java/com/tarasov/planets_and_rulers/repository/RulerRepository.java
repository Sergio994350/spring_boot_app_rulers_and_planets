package com.tarasov.planets_and_rulers.repository;

import com.tarasov.planets_and_rulers.model.Ruler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RulerRepository extends JpaRepository<Ruler, Long> {

    List<Ruler> findTop10ByOrderByAgeAsc();

    List<Ruler> findAllByName(String name);

    Ruler findByName(String name);
}
