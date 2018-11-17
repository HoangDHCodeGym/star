package com.codegym.star.service;

import com.codegym.star.model.Star;
import com.codegym.star.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService {
    @Autowired
    StarRepository starRepository;
    @Override
    public Iterable<Star> findAll() {
        return starRepository.findAll();
    }

    @Override
    public Iterable<Star> findByCodeOrName(String query) {
        return starRepository.findByCodeContainsOrNameContainsAllIgnoreCase(query, query);
    }

    @Override
    public Star findByCode(String code) {
        return starRepository.findById(code).get();
    }

    @Override
    public void save(Star star) {
        starRepository.save(star);
    }

    @Override
    public void deleteByCode(String code) {
        starRepository.deleteById(code);
    }
}
