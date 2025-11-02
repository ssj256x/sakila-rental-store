package com.backend.sakila.repository;

import com.backend.sakila.model.Actor;
import com.backend.sakila.model.Category;
import com.backend.sakila.model.Film;
import com.backend.sakila.model.FilmFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmFilterRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Film> findFilmsByFilter(FilmFilter filter, int limit, int offset) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Film> cq = cb.createQuery(Film.class);
        Root<Film> film = cq.from(Film.class);
        cq.select(film).distinct(true);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getTitle() != null) {
            predicates.add(cb.like(cb.lower(film.get("title")), "%" + filter.getTitle().toLowerCase() + "%"));
        }
        if (filter.getDescription() != null) {
            predicates.add(cb.like(cb.lower(film.get("description")), "%" + filter.getDescription().toLowerCase() + "%"));
        }
        if (filter.getReleaseYear() != null) {
            predicates.add(cb.equal(film.get("releaseYear"), filter.getReleaseYear()));
        }
        if (filter.getLanguage() != null) {
            predicates.add(cb.equal(film.get("language").get("name"), filter.getLanguage()));
        }

        // categories: join the categories collection (many-to-many)
        if (filter.getCategories() != null && !filter.getCategories().isEmpty()) {
            // Use LEFT join to not exclude films that might have NULL categories (if that's possible)
            Join<Film, Category> categoryJoin = film.join("categories", JoinType.LEFT);
            predicates.add(categoryJoin.get("name").in(filter.getCategories()));
        }

        if (filter.getLength() != null) {
            predicates.add(cb.equal(film.get("length"), filter.getLength()));
        }

        if (filter.getRating() != null) {
            predicates.add(cb.equal(film.get("rating"), filter.getRating()));
        }

        if (filter.getActors() != null && !filter.getActors().isEmpty()) {
            Join<Film, Actor> actorJoin = film.join("actors", JoinType.LEFT);
            List<Predicate> actorPreds = new ArrayList<>();
            for (String actorName : filter.getActors()) {
                actorPreds.add(cb.or(
                        cb.like(cb.lower(actorJoin.get("firstName")), "%" + actorName.toLowerCase() + "%"),
                        cb.like(cb.lower(actorJoin.get("lastName")), "%" + actorName.toLowerCase() + "%")
                ));
            }
            predicates.add(cb.or(actorPreds.toArray(new Predicate[0])));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        // Sorting (example)
        if (filter.getSortBy() != null) {
            Path<?> sortPath = switch (filter.getSortBy()) {
                case "releaseYear" -> film.get("releaseYear");
                case "rating" -> film.get("rating");
                default -> film.get("title");
            };

            cq.orderBy("desc".equalsIgnoreCase(filter.getSortDirection()) ? cb.desc(sortPath) : cb.asc(sortPath));
        }

        TypedQuery<Film> query = em.createQuery(cq);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}
