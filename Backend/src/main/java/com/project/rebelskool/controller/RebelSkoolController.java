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

import com.project.rebelskool.repo.OrganizationRepo;
import com.project.rebelskool.repo.IncomeStatementHeadersRepo;
import com.project.rebelskool.repo.CarouselRepo;
import com.project.rebelskool.implementation.OrganizationImpl;
import com.project.rebelskool.implementation.IncomeStatementImpl;
import com.project.rebelskool.implementation.CarouselImpl;
import org.springframework.web.client.RestTemplate;

@RestController
public class RebelSkoolController implements CommandLineRunner {

@Autowired
private OrganizationRepo organizationRepo;

@Autowired
private IncomeStatementHeadersRepo IncomeStatementHeadersRepo;

@Autowired
private CarouselRepo CarouselRepo;

@Autowired
private OrganizationImpl organizationImpl;

@Autowired
private IncomeStatementImpl IncomeStatementImpl;

@Autowired
private CarouselImpl CarouselImpl;


    @Override
    public void run(String... strings) throws Exception {
    }

    @GetMapping("/getAllOrganization")
    public List<Organization> getAllOrganization() {
        return organizationRepo.findAll();
    }

    @GetMapping("/getOrganizationDetails/{id}")
    public List<Organization> getOrganizationDetailsByIdX (@PathVariable String id) throws IOExceptio {
        List<Organization> organization = new ArrayList<>();
        organization = organizationRepo.getByOrganizationId(id);
        organization = organizationImpl.getOrganizationDetails(organization);
        return organization;
    }

    @GetMapping("/getOrganizationCeo")
    public List<Organization> getOrganizationCeo (@RequestParam(value = "ceo") String ceo) throws IOExceptio {
//        List<Organization> organization = new ArrayList<>();
//        organization = organizationRepo.getByCeo(ceo);
//        organization = organizationImpl.getOrganizationDetails(organization);
        return organizationImpl.getCeo(ceo);
    }


    @GetMapping("/getDetailsFromEdeklarera/{id}")
    public String getDetailsFromEdeklarera (@PathVariable String id) throws IOExceptio {
        String uri = "https://edeklarera.se/api/company/"+id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri,String.class);
        return result;
    }

    @PostMapping("/insertOrganization")
    public String insertOrganization (@RequestBody Organization organizationParam) throws IOExceptio {
        organizationRepo.insertOrganization(organizationParam.organizationname , organizationParam.organizationno,
                organizationParam.ceo, organizationParam.phone, organizationParam.address, organizationParam.revenue);
        return "Inserted";
    }
//
//    @GetMapping("/getIncomeStatementHeaders")
//        public List<IncomeStatementHeaders> getIncomeStatementFields() {
//        List<IncomeStatementHeaders> IncomeStatementHeadersList = new ArrayList<>();
//        IncomeStatementHeadersList = IncomeStatementHeadersRepo.findAll();
//        return IncomeStatementHeadersList;
//        }

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
