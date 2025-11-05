package com.backend.sakila.mappers;

import com.backend.sakila.api.model.Film;
import com.backend.sakila.model.entity.FilmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ActorAndActorEntityMapper.class})
public interface FilmAndFilmEntityMapper {

    /**
     * Map FilmEntity to Film.
     *
     * @param filmEntity - FilmEntity to be mapped.
     * @return Film - mapped Film.
     */
    @Mapping(source = "language.name", target = "language")
    Film filmEntityToFilm(FilmEntity filmEntity);

    /**
     * Map Film to FilmEntity.
     *
     * @param film - Film to be mapped.
     * @return FilmEntity - mapped FilmEntity.
     */
    @Mapping(source = "language", target = "language.name")
    FilmEntity filmToFilmEntity(Film film);
}
