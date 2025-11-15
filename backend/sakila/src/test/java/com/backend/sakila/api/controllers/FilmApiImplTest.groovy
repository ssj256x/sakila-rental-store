package com.backend.sakila.api.controllers

import com.backend.sakila.api.model.Film
import com.backend.sakila.exceptions.AppException
import com.backend.sakila.services.FilmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification
import spock.lang.Subject

import static com.backend.sakila.exceptions.ErrorCodes.DATA_NOT_FOUND
import static com.backend.sakila.exceptions.ErrorCodes.REQUEST_PROCESS_ERROR
import static org.mockito.Mockito.*

@SpringBootTest
class FilmApiImplSpec extends Specification {

    @Autowired
    @Subject
    FilmApiImpl filmApiImpl

    @MockBean
    FilmService filmService

    // ---------------------------------------------------------
    // listLanguages()
    // ---------------------------------------------------------

    def "listLanguages returns list of languages"() {
        given:
        def languages = ["English", "French"]
        when(filmService.getAllLanguages()).thenReturn(languages)

        when:
        def result = filmApiImpl.listLanguages()

        then:
        result == languages
        1 * filmService.getAllLanguages()
    }

    def "listLanguages returns empty list when service returns empty"() {
        given:
        when(filmService.getAllLanguages()).thenReturn([])

        when:
        def result = filmApiImpl.listLanguages()

        then:
        result.isEmpty()
        1 * filmService.getAllLanguages()
    }

    def "listLanguages throws AppException when service layer throws"() {
        given:
        when(filmService.getAllLanguages()).thenThrow(new AppException(REQUEST_PROCESS_ERROR))

        when:
        filmApiImpl.listLanguages()

        then:
        thrown(AppException)
        1 * filmService.getAllLanguages()
    }

    // ---------------------------------------------------------
    // listCategories()
    // ---------------------------------------------------------

    def "listCategories returns list of categories"() {
        given:
        def categories = ["Action", "Drama"]
        when(filmService.getAllCategories()).thenReturn(categories)

        when:
        def result = filmApiImpl.listCategories()

        then:
        result == categories
        1 * filmService.getAllCategories()
    }

    def "listCategories returns empty list when service returns empty"() {
        given:
        when(filmService.getAllCategories()).thenReturn([])

        when:
        def result = filmApiImpl.listCategories()

        then:
        result.isEmpty()
        1 * filmService.getAllCategories()
    }

    def "listCategories throws AppException when service layer fails"() {
        given:
        when(filmService.getAllCategories()).thenThrow(new AppException(REQUEST_PROCESS_ERROR))

        when:
        filmApiImpl.listCategories()

        then:
        thrown(AppException)
        1 * filmService.getAllCategories()
    }

    // ---------------------------------------------------------
    // listRatings()
    // ---------------------------------------------------------

    def "listRatings returns list of ratings"() {
        given:
        def ratings = ["G", "PG-13"]
        when(filmService.getAllRatings()).thenReturn(ratings)

        when:
        def result = filmApiImpl.listRatings()

        then:
        result == ratings
        1 * filmService.getAllRatings()
    }

    def "listRatings returns empty list when service returns empty"() {
        given:
        when(filmService.getAllRatings()).thenReturn([])

        when:
        def result = filmApiImpl.listRatings()

        then:
        result.isEmpty()
        1 * filmService.getAllRatings()
    }

    def "listRatings throws AppException for service error"() {
        given:
        when(filmService.getAllRatings()).thenThrow(new AppException(REQUEST_PROCESS_ERROR))

        when:
        filmApiImpl.listRatings()

        then:
        thrown(AppException)
        1 * filmService.getAllRatings()
    }

    // ---------------------------------------------------------
    // getFilmById()
    // ---------------------------------------------------------

    def "getFilmById returns a film when valid id is given"() {
        given:
        def film = new Film(id: 5, title: "Test")
        when(filmService.findFilmById(5)).thenReturn(film)

        when:
        def result = filmApiImpl.getFilmById(5)

        then:
        result.id == 5
        1 * filmService.findFilmById(5)
    }

    def "getFilmById throws AppException when film not found"() {
        given:
        when(filmService.findFilmById(999)).thenThrow(new AppException(DATA_NOT_FOUND))

        when:
        filmApiImpl.getFilmById(999)

        then:
        thrown(AppException)
        1 * filmService.findFilmById(999)
    }

    def "getFilmById throws AppException when service fails unexpectedly"() {
        given:
        when(filmService.findFilmById(5)).thenThrow(new AppException(REQUEST_PROCESS_ERROR))

        when:
        filmApiImpl.getFilmById(5)

        then:
        thrown(AppException)
        1 * filmService.findFilmById(5)
    }

    def "getFilmById propagates null ID to service and handles AppException"() {
        given:
        when(filmService.findFilmById(null)).thenThrow(new AppException(DATA_NOT_FOUND))

        when:
        filmApiImpl.getFilmById(null)

        then:
        thrown(AppException)
        1 * filmService.findFilmById(null)
    }
}