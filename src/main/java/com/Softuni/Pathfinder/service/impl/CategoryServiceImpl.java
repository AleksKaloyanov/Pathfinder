package com.Softuni.Pathfinder.service.impl;

import com.Softuni.Pathfinder.model.entity.CategoryEntity;
import com.Softuni.Pathfinder.model.entity.enums.CategoryNameEnum;
import com.Softuni.Pathfinder.repository.CategoryRepository;
import com.Softuni.Pathfinder.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository
                .findByName(categoryNameEnum)
                .orElse(null);
    }
}
