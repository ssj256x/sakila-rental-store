package com.backend.sakila.services;

import com.backend.sakila.model.Category;
import com.backend.sakila.model.Language;
import com.backend.sakila.repository.CategoryRepository;
import com.backend.sakila.repository.FilmRepository;
import com.backend.sakila.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;
    private final FilmRepository filmRepository;

    public List<String> getAllLanguages() {
        return languageRepository.findAll().stream()
                .map(Language::getName)
                .map(String::trim)
                .toList();
    }

    public List<String> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(Category::getName)
                .map(String::trim)
                .toList();
    }

    public List<String> getAllRatings() {
        return filmRepository.findAllRatings().stream()
                .map(String::trim)
                .toList();
    }
}
