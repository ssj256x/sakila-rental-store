package com.backend.sakila.controllers;

import com.backend.sakila.model.entity.FilmEntity;
import com.backend.sakila.model.graphql.FilmFilter;
import com.backend.sakila.services.FilmFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmFilterController {

    private final FilmFilterService filmFilterService;

    @QueryMapping
    public List<FilmEntity> films(@Argument("filter") FilmFilter filmFilter,
                                  @Argument Integer limit,
                                  @Argument Integer offset) {
        int _limit = limit != null ? limit : 10;
        int _offset = offset != null ? offset : 0;
        return filmFilterService.search(filmFilter, _limit, _offset);
    }
}
