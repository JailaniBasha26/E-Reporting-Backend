package com.project.rebelskool.implementation;
import com.project.rebelskool.entity.*;
import com.project.rebelskool.repo.OrganizationRepo;
import com.project.rebelskool.repo.IncomeStatementFieldsRepo;
import com.project.rebelskool.repo.IncomeStatementHeadersRepo;
import com.project.rebelskool.repo.CarouselRepo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarouselImpl {
    @Autowired
    private CarouselRepo CarouselRepo;

    public List<String> UrlList() throws IOExceptio{
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
