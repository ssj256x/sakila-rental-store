package com.backend.sakila.controllers;

import com.backend.sakila.services.MetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MetaController {

    private final MetaService metaService;

    @GetMapping("/languages")
    public List<String> getLanguages() {
        return metaService.getAllLanguages();
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return metaService.getAllCategories();
    }

    @GetMapping("/ratings")
    public List<String> getRatings() {
        return metaService.getAllRatings();
    }
}
