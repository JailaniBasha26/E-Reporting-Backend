package com.rebelskool.implementation;

import com.rebelskool.entity.*;
import com.rebelskool.repo.CarouselRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselImpl {
    @Autowired
    private CarouselRepo CarouselRepo;

    public List<String> UrlList() throws IOException {
        List<CarouselUrl> urls = new ArrayList<>();
        urls = CarouselRepo.findAll();

        List<String> urlList = new ArrayList<>();
        if (urls.size() > 0 ) {
            urls.forEach(
                    (result) -> {
                        urlList.add(result.url);
                    }
                    );
        }
        return urlList;
    }
}
