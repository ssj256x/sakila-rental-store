package com.backend.sakila.services;

import com.backend.sakila.repository.FilmFilterRepository;
import com.backend.sakila.model.entity.FilmEntity;
import com.backend.sakila.model.graphql.FilmFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmFilterService {

    private final FilmFilterRepository filmRepository;

    public List<FilmEntity> search(FilmFilter filter, int limit, int offset) {
        return filmRepository.findFilmsByFilter(filter, limit, offset);
    }
}
