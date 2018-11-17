package com.codegym.star.repository;

import com.codegym.star.model.Star;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StarRepository extends PagingAndSortingRepository<Star, String> {
    Iterable<Star> findByCodeContainsOrNameContainsAllIgnoreCase(String code, String name);
}
