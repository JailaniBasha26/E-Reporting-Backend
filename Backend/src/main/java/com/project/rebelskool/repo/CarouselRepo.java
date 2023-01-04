package com.project.rebelskool.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.rebelskool.entity.CarouselUrl;

@Repository
public interface CarouselRepo extends JpaRepository<CarouselUrl, String>{
    @Query(value = "select * from carouselurl", nativeQuery = true)
    List<CarouselUrl> getAllUrl();
}
