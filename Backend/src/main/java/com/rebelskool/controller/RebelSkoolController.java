package com.rebelskool.controller;

import com.rebelskool.entity.*;
import com.rebelskool.implementation.IOExceptio;
import com.rebelskool.repo.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rebelskool.implementation.IncomeStatementImpl;
import com.rebelskool.implementation.CarouselImpl;
import com.rebelskool.implementation.OrganizationImpl;
import org.springframework.web.client.RestTemplate;

@RestController
public class RebelSkoolController implements CommandLineRunner {

@Autowired
private IncomeStatementImpl IncomeStatementImpl;

@Autowired
private CarouselImpl CarouselImpl;

@Autowired
private OrganizationImpl OrganizationImpl;

@Autowired
private OrganizationRepo OrganizationRepo;

    @Override
    public void run(String... strings) throws Exception {
    }
    @GetMapping("/getDetailsFromEdeklarera/{id}")
    public String getDetailsFromEdeklarera (@PathVariable String id) throws IOException {
        String uri = "https://edeklarera.se/api/company/"+id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri,String.class);
        return result;
    }

    @GetMapping("/getIncomeStatementFields")
    public Map<String,List<FieldDetails>> getIncomeStatementDetails()  throws IOException {
        Map<String,List<FieldDetails>> incomeStatementFieldsDetails = new HashMap<>();
        incomeStatementFieldsDetails = IncomeStatementImpl.getIcomeStatementHeaderDetails();
        return incomeStatementFieldsDetails;
    }

    @GetMapping("/getIncomeStatementFieldsByYear/{year}")
    public Map<String,List<FieldDetails>> getIncomeStatementFieldsByYear(@PathVariable Integer year)  throws IOException {
        Map<String,List<FieldDetails>> incomeStatementFieldsDetails = new HashMap<>();
        incomeStatementFieldsDetails = IncomeStatementImpl.getIncomeStatementFieldsByYear(year);
        return incomeStatementFieldsDetails;
    }

    @GetMapping("/getIncomeStatementFieldsByFinancialYears/{year}")
    public Map<String,List<FieldDetails>> getIncomeStatementFieldsByFinancialYears(@PathVariable String year)  throws IOException {
        Map<String,List<FieldDetails>> incomeStatementFieldsDetails = new HashMap<>();
        incomeStatementFieldsDetails = IncomeStatementImpl.getIncomeStatementFieldsByFinancialYears(year);
        return incomeStatementFieldsDetails;
    }

    @GetMapping("/getCarouselImages")
    public List<String> getCarouselImages()  throws IOException {
        List<String> urlList = new ArrayList<>();
        urlList = CarouselImpl.UrlList();
        return urlList;
    }

    @GetMapping("/getOrganizationDetails/{organizationNo}")
    public Organization getOrganizationDetails(@PathVariable String organizationNo)  throws IOException {
        Organization organization = new Organization();
        organization = OrganizationImpl.getOrganizationDetailsById(organizationNo);
        return organization;
    }

    @PostMapping("/postOrganizationDetails")
    public String postOrganizationDetails(@RequestBody Organization organizationParam)  throws IOException {
        OrganizationRepo.addNewOrganization(organizationParam.organizationname, organizationParam.organizationno,organizationParam.zipcode,organizationParam.postaladdress);
        return "Inserted";
    }
}
