package com.backend.sakila.api.controllers;

import com.backend.sakila.api.model.Film;
import com.backend.sakila.mappers.FilmAndFilmEntityMapper;
import com.backend.sakila.model.entity.FilmEntity;
import com.backend.sakila.model.graphql.FilmFilter;
import com.backend.sakila.services.FilmFilterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@GraphQlTest(FilmFilterController.class)
class FilmFilterControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FilmFilterService filmFilterService;

    @MockitoBean
    private FilmAndFilmEntityMapper filmAndFilmEntityMapper;

    @Test
    @DisplayName("Should return a list of films from GraphQL query")
    void testFilms() {

        // GIVEN
        FilmFilter filter = new FilmFilter();
        filter.setTitle("Matrix");

        FilmEntity entity = new FilmEntity();
        entity.setId(1);
        entity.setTitle("The Matrix");

        Film model = new Film();
        model.setFilmId(1);
        model.setTitle("The Matrix");

        Mockito.when(filmFilterService.search(any(FilmFilter.class), eq(10), eq(0)))
                .thenReturn(List.of(entity));

        Mockito.when(filmAndFilmEntityMapper.filmEntityToFilm(entity))
                .thenReturn(model);

        Map<String, Object> filterMap = objectMapper.convertValue(filter, Map.class);

        // WHEN + THEN
        String query = """
            query($filter: FilmFilter) {
              films(filter: $filter) {
                title
              }
            }
            """;

        graphQlTester.document(query)
                .variable("filter", filterMap)
                .execute()
                .path("films")
                .entityList(Object.class)
                .hasSize(1)
                .path("films[0].title").entity(String.class).isEqualTo("The Matrix");
    }

    @Test
    @DisplayName("Should apply default limit=10 and offset=0 when not provided")
    void testDefaultPagination() {

        FilmEntity entity = new FilmEntity();
        entity.setId(2);
        entity.setTitle("Avatar");

        Film model = new Film();
        model.setFilmId(2);
        model.setTitle("Avatar");

        Mockito.when(filmFilterService.search(any(), eq(10), eq(0)))
                .thenReturn(List.of(entity));

        Mockito.when(filmAndFilmEntityMapper.filmEntityToFilm(entity))
                .thenReturn(model);

        String query = """
                query {
                  films {
                    title
                  }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("films")
                .entityList(Film.class)
                .hasSize(1);
    }

    @Test
    @DisplayName("Should use provided limit and offset")
    void testCustomPagination() {

        Mockito.when(filmFilterService.search(any(), eq(5), eq(20)))
                .thenReturn(List.of());

        String query = """
                query {
                  films(limit: 5, offset: 20) {
                    title
                  }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("films")
                .entityList(Film.class)
                .hasSize(0);

        Mockito.verify(filmFilterService)
                .search(any(), eq(5), eq(20));
    }

    @Test
    @DisplayName("Should return empty list when no films found")
    void testEmptyResults() {

        Mockito.when(filmFilterService.search(any(), eq(10), eq(0)))
                .thenReturn(List.of());

        String query = """
                query {
                  films {
                    title
                  }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("films")
                .entityList(Film.class)
                .hasSize(0);
    }
}