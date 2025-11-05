package com.backend.sakila.mappers;

import com.backend.sakila.api.model.Category;
import com.backend.sakila.model.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryAndCategoryEntityMapper {

    /**
     * Map CategoryEntity to Category.
     * @param categoryEntity - CategoryEntity to be mapped.
     * @return Category - mapped Category.
     */
    Category categoryToCategoryEntity(CategoryEntity categoryEntity);

    /**
     * Map Category to CategoryEntity.
     * @param category - Category to be mapped.
     * @return CategoryEntity - mapped CategoryEntity.
     */
    CategoryEntity categoryEntityToCategory(Category category);
}
