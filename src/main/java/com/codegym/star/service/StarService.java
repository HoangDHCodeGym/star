package com.codegym.star.service;

import com.codegym.star.model.Star;

public interface StarService {
    Iterable<Star> findAll();
    Iterable<Star> findByCodeOrName(String query);
    Star findByCode(String code);
    void save(Star star);
    void deleteByCode(String code);
}
