package com.Softuni.Pathfinder.repository;

import com.Softuni.Pathfinder.model.entity.CategoryEntity;
import com.Softuni.Pathfinder.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(CategoryNameEnum name);

}
