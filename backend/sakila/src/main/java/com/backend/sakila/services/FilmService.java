package com.backend.sakila.services;

import com.backend.sakila.api.model.Film;
import com.backend.sakila.exceptions.AppException;
import com.backend.sakila.exceptions.ErrorCodes;
import com.backend.sakila.mappers.FilmAndFilmEntityMapper;
import com.backend.sakila.model.entity.CategoryEntity;
import com.backend.sakila.model.entity.LanguageEntity;
import com.backend.sakila.repository.CategoryRepository;
import com.backend.sakila.repository.FilmRepository;
import com.backend.sakila.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;
    private final FilmRepository filmRepository;
    private final FilmAndFilmEntityMapper filmAndFilmEntityMapper;

    public List<String> getAllLanguages() {
        return languageRepository.findAll().stream()
                .map(LanguageEntity::getName)
                .map(String::trim)
                .toList();
    }

    public List<String> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryEntity::getName)
                .map(String::trim)
                .toList();
    }

    public List<String> getAllRatings() {
        return filmRepository.findAllRatings().stream()
                .map(String::trim)
                .toList();
    }

    public Film findFilmById(Integer id) {
        return filmRepository.findById(id)
                .map(filmAndFilmEntityMapper::filmEntityToFilm)
                .orElseThrow(() -> new AppException(ErrorCodes.DATA_NOT_FOUND));
    }
}
