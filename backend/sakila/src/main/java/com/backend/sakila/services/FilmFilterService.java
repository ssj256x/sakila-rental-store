package com.backend.sakila.services;

import com.backend.sakila.repository.FilmFilterRepository;
import com.backend.sakila.model.Film;
import com.backend.sakila.model.FilmFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmFilterService {

    private final FilmFilterRepository filmRepository;

    public List<Film> search(FilmFilter filter, int limit, int offset) {
        return filmRepository.findFilmsByFilter(filter, limit, offset);
    }
}
