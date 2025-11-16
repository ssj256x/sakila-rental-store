package com.backend.sakila.api.controllers;

import com.backend.sakila.api.model.Film;
import com.backend.sakila.exceptions.AppException;
import com.backend.sakila.services.FilmService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static com.backend.sakila.exceptions.ErrorCodes.DATA_NOT_FOUND;
import static com.backend.sakila.exceptions.ErrorCodes.REQUEST_PROCESS_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FilmApiImplTest {

    @Autowired
    FilmApiImpl filmApiImpl;

    @MockitoBean
    FilmService filmService;

    // ---------------------------------------------------------
    // listLanguages()
    // ---------------------------------------------------------

    @Test
    @DisplayName("listLanguages returns list of languages")
    void listLanguagesReturnsList() {
        List<String> languages = List.of("English", "French");
        when(filmService.getAllLanguages()).thenReturn(languages);

        List<String> result = filmApiImpl.listLanguages();

        assertEquals(languages, result);
        verify(filmService, times(1)).getAllLanguages();
    }

    @Test
    @DisplayName("listLanguages returns empty list when service returns empty")
    void listLanguagesReturnsEmptyList() {
        when(filmService.getAllLanguages()).thenReturn(List.of());

        List<String> result = filmApiImpl.listLanguages();

        assertTrue(result.isEmpty());
        verify(filmService, times(1)).getAllLanguages();
    }

    @Test
    @DisplayName("listLanguages throws AppException when service layer throws")
    void listLanguagesThrowsException() {
        when(filmService.getAllLanguages()).thenThrow(new AppException(REQUEST_PROCESS_ERROR));

        assertThrows(AppException.class, () -> filmApiImpl.listLanguages());
        verify(filmService, times(1)).getAllLanguages();
    }

    // ---------------------------------------------------------
    // listCategories()
    // ---------------------------------------------------------

    @Test
    @DisplayName("listCategories returns list of categories")
    void listCategoriesReturnsList() {
        List<String> categories = List.of("Action", "Drama");
        when(filmService.getAllCategories()).thenReturn(categories);

        List<String> result = filmApiImpl.listCategories();

        assertEquals(categories, result);
        verify(filmService, times(1)).getAllCategories();
    }

    @Test
    @DisplayName("listCategories returns empty list when service returns empty")
    void listCategoriesReturnsEmptyList() {
        when(filmService.getAllCategories()).thenReturn(List.of());

        List<String> result = filmApiImpl.listCategories();

        assertTrue(result.isEmpty());
        verify(filmService, times(1)).getAllCategories();
    }

    @Test
    @DisplayName("listCategories throws AppException when service layer fails")
    void listCategoriesThrowsException() {
        when(filmService.getAllCategories()).thenThrow(new AppException(REQUEST_PROCESS_ERROR));

        assertThrows(AppException.class, () -> filmApiImpl.listCategories());
        verify(filmService, times(1)).getAllCategories();
    }

    // ---------------------------------------------------------
    // listRatings()
    // ---------------------------------------------------------

    @Test
    @DisplayName("listRatings returns list of ratings")
    void listRatingsReturnsList() {
        List<String> ratings = List.of("G", "PG-13");
        when(filmService.getAllRatings()).thenReturn(ratings);

        List<String> result = filmApiImpl.listRatings();

        assertEquals(ratings, result);
        verify(filmService, times(1)).getAllRatings();
    }

    @Test
    @DisplayName("listRatings returns empty list when service returns empty")
    void listRatingsReturnsEmptyList() {
        when(filmService.getAllRatings()).thenReturn(List.of());

        List<String> result = filmApiImpl.listRatings();

        assertTrue(result.isEmpty());
        verify(filmService, times(1)).getAllRatings();
    }

    @Test
    @DisplayName("listRatings throws AppException for service error")
    void listRatingsThrowsException() {
        when(filmService.getAllRatings()).thenThrow(new AppException(REQUEST_PROCESS_ERROR));

        assertThrows(AppException.class, () -> filmApiImpl.listRatings());
        verify(filmService, times(1)).getAllRatings();
    }

    // ---------------------------------------------------------
    // getFilmById()
    // ---------------------------------------------------------

    @Test
    @DisplayName("getFilmById returns a film when valid id is given")
    void getFilmByIdSuccess() {
        Film film = new Film();
        film.setFilmId(5);
        film.setTitle("Test");

        when(filmService.findFilmById(5)).thenReturn(film);

        Film result = filmApiImpl.getFilmById(5);

        assertEquals(5, result.getFilmId());
        verify(filmService, times(1)).findFilmById(5);
    }

    @Test
    @DisplayName("getFilmById throws AppException when film not found")
    void getFilmByIdNotFound() {
        when(filmService.findFilmById(999)).thenThrow(new AppException(DATA_NOT_FOUND));

        assertThrows(AppException.class, () -> filmApiImpl.getFilmById(999));
        verify(filmService, times(1)).findFilmById(999);
    }

    @Test
    @DisplayName("getFilmById throws AppException when service fails unexpectedly")
    void getFilmByIdUnexpectedFailure() {
        when(filmService.findFilmById(5)).thenThrow(new AppException(REQUEST_PROCESS_ERROR));

        assertThrows(AppException.class, () -> filmApiImpl.getFilmById(5));
        verify(filmService, times(1)).findFilmById(5);
    }

    @Test
    @DisplayName("getFilmById propagates null ID and handles AppException")
    void getFilmByIdNullId() {
        when(filmService.findFilmById(null)).thenThrow(new AppException(DATA_NOT_FOUND));

        assertThrows(AppException.class, () -> filmApiImpl.getFilmById(null));
        verify(filmService, times(1)).findFilmById(null);
    }
}