package com.project.rebelskool.repo;
import java.io.IOException;
import java.util.List;

import com.project.rebelskool.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.rebelskool.entity.CarouselUrl;

@Repository
public interface CarouselRepo extends JpaRepository<CarouselUrl, String>{
    @Query(value = "select * from carouselurl", nativeQuery = true)
    List<CarouselUrl> getAllUrl();
}
