package com.backend.sakila.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FilmFilter {
    private String title;
    private String description;
    private Integer releaseYear;
    private String language;
    private List<String> categories;
    private Integer length;
    private String rating;
    private List<String> actors;

    // Sorting options
    private String sortBy; // e.g., "title", "releaseYear", "rating"
    private String sortDirection; // "asc" or "desc"
}
