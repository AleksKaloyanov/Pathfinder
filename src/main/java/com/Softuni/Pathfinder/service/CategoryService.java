package com.Softuni.Pathfinder.service;

import com.Softuni.Pathfinder.model.entity.CategoryEntity;
import com.Softuni.Pathfinder.model.entity.enums.CategoryNameEnum;

import java.util.Optional;

public interface CategoryService {

    CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum);
}
