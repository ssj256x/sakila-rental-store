package com.backend.sakila.api.controllers;

import com.backend.sakila.api.controller.FilmsApi;
import com.backend.sakila.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilmApiImpl implements FilmsApi {

    public final FilmService filmService;

    /**
     * GET /api/v1/films/languages: List of languages
     *
     * @return List of available languages (status code 200)
     */
    @Override
    public List<String> listLanguages() {
        return filmService.getAllLanguages();
    }

    /**
     * GET /api/v1/films/categories: List of categories
     *
     * @return List of available categories (status code 200)
     */
    @Override
    public List<String> listCategories() {
        return filmService.getAllCategories();
    }

    /**
     * GET /api/v1/films/ratings: List of ratings
     *
     * @return List of available ratings (status code 200)
     */
    @Override
    public List<String> listRatings() {
        return filmService.getAllRatings();
    }
}
