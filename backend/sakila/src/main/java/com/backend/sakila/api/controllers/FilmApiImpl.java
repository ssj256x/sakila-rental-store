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
     * GET /api/v1/films/languages : List of languages
     *
     * @return List of available languages (status code 200)
     */
    @Override
    public List<String> listLanguages() {
        return FilmsApi.super.listLanguages();
    }

    @Override
    public List<String> listCategories() {
        return FilmsApi.super.listCategories();
    }

    @Override
    public List<String> listRatings() {
        return FilmsApi.super.listRatings();
    }
}
