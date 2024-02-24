package com.Softuni.Pathfinder.repository;

import com.Softuni.Pathfinder.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

    @Query("select p.url from PictureEntity p")
    List<String> findAllUrls();
}
