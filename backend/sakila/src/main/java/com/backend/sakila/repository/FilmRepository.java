package com.backend.sakila.repository;

import com.backend.sakila.model.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
    @Query("SELECT DISTINCT f.rating FROM FilmEntity f")
    List<String> findAllRatings();
}
