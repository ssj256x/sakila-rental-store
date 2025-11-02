package com.backend.sakila.repository;

import com.backend.sakila.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Query("SELECT DISTINCT f.rating FROM Film f")
    List<String> findAllRatings();
}
