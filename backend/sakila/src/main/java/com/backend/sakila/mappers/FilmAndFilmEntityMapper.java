package com.backend.sakila.mappers;

import com.backend.sakila.api.model.Film;
import com.backend.sakila.model.entity.FilmEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ActorAndActorEntityMapper.class, CategoryAndCategoryEntityMapper.class})
public interface FilmAndFilmEntityMapper {

    /**
     * Map FilmEntity to Film.
     *
     * @param filmEntity - FilmEntity to be mapped.
     * @return Film - mapped Film.
     */
    @Mapping(source = "language.name", target = "language")
    @Mapping(source = "rating", target = "rating")
    Film filmEntityToFilm(FilmEntity filmEntity);

    /**
     * Map Film to FilmEntity.
     *
     * @param film - Film to be mapped.
     * @return FilmEntity - mapped FilmEntity.
     */
    @Mapping(source = "language", target = "language.name")
    @Mapping(source = "rating", target = "rating")
    FilmEntity filmToFilmEntity(Film film);

    default Film.RatingEnum mapRating(String rating) {
        if (rating == null) return null;
        return switch (rating) {
            case "G" -> Film.RatingEnum.G;
            case "PG" -> Film.RatingEnum.PG;
            case "PG-13" -> Film.RatingEnum.PG_13;
            case "R" -> Film.RatingEnum.R;
            case "NC-17" -> Film.RatingEnum.NC_17;
            default -> throw new IllegalArgumentException("Unsupported rating: " + rating);
        };
    }

    default String mapRating(Film.RatingEnum rating) {
        if (rating == null) return null;
        return switch (rating) {
            case G -> "G";
            case PG -> "PG";
            case PG_13 -> "PG-13";
            case R -> "R";
            case NC_17 -> "NC-17";
        };
    }
}
