package com.project.rebelskool.controller;

import com.project.rebelskool.entity.*;
import com.project.rebelskool.implementation.IOExceptio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.project.rebelskool.implementation.IncomeStatementImpl;
import com.project.rebelskool.implementation.CarouselImpl;
import org.springframework.web.client.RestTemplate;

@RestController
public class RebelSkoolController implements CommandLineRunner {

@Autowired
private IncomeStatementImpl IncomeStatementImpl;

@Autowired
private CarouselImpl CarouselImpl;
    @Override
    public void run(String... strings) throws Exception {
    }
    @GetMapping("/getDetailsFromEdeklarera/{id}")
    public String getDetailsFromEdeklarera (@PathVariable String id) throws IOExceptio {
        String uri = "https://edeklarera.se/api/company/"+id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri,String.class);
        return result;
    }

    @GetMapping("/getIncomeStatementFields")
    public Map<String,List<FieldDetails>> getIncomeStatementDetails()  throws IOExceptio {
        Map<String,List<FieldDetails>> incomeStatementFieldsDetails = new HashMap<>();
        incomeStatementFieldsDetails = IncomeStatementImpl.getIcomeStatementHeaderDetails();
        return incomeStatementFieldsDetails;
    }

    @GetMapping("/getCarouselImages")
    public List<String> getCarouselImages()  throws IOExceptio {
        List<String> urlList = new ArrayList<>();
        urlList = CarouselImpl.UrlList();
        return urlList;
    }
}
