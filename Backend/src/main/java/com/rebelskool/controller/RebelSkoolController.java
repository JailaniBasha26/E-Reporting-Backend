package com.rebelskool.controller;

import com.rebelskool.entity.*;
import com.rebelskool.repo.OrganizationRepo;
import com.rebelskool.repo.UserDetailsRepo;
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

    @Autowired
    private UserDetailsRepo UserDetailsRepo;

    @Override
    public void run(String... strings) throws Exception {
    }

    @GetMapping("/getDetailsFromEdeklarera/{id}")
    public String getDetailsFromEdeklarera(@PathVariable String id) throws IOException {
        String uri = "https://edeklarera.se/api/company/" + id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @GetMapping("/getIncomeStatementFields")
    public Map<String, List<FieldDetails>> getIncomeStatementDetails() throws IOException {
        Map<String, List<FieldDetails>> incomeStatementFieldsDetails = new HashMap<>();
        incomeStatementFieldsDetails = IncomeStatementImpl.getIcomeStatementHeaderDetails();
        return incomeStatementFieldsDetails;
    }

    @GetMapping("/getIncomeStatementFieldsByYear/{year}")
    public Map<String, List<FieldDetails>> getIncomeStatementFieldsByYear(@PathVariable Integer year) throws IOException {
        Map<String, List<FieldDetails>> incomeStatementFieldsDetails = new HashMap<>();
        incomeStatementFieldsDetails = IncomeStatementImpl.getIncomeStatementFieldsByYear(year);
        return incomeStatementFieldsDetails;
    }

    @GetMapping("/getIncomeStatementFieldsByFinancialYears/{year}")
    public Map<String, List<FieldDetails>> getIncomeStatementFieldsByFinancialYears(@PathVariable String year) throws IOException {
        Map<String, List<FieldDetails>> incomeStatementFieldsDetails = new HashMap<>();
        incomeStatementFieldsDetails = IncomeStatementImpl.getIncomeStatementFieldsByFinancialYears(year);
        return incomeStatementFieldsDetails;
    }

    @GetMapping("/getCarouselImages")
    public List<String> getCarouselImages() throws IOException {
        List<String> urlList = new ArrayList<>();
        urlList = CarouselImpl.UrlList();
        return urlList;
    }

    @GetMapping("/getOrganizationDetails/{organizationNo}")
    public Organization getOrganizationDetails(@PathVariable String organizationNo) throws IOException {
        Organization organization = new Organization();
        organization = OrganizationImpl.getOrganizationDetailsById(organizationNo);
        return organization;
    }

    @PostMapping("/postOrganizationDetails")
    public String postOrganizationDetails(@RequestBody Organization organizationParam) throws IOException {
        OrganizationRepo.addNewOrganization(organizationParam.organizationname, organizationParam.organizationno, organizationParam.zipcode, organizationParam.postaladdress);
        return "Inserted";
    }

    //SIGN-UP
    @PostMapping("/createAccount")
    public String insertUser(@RequestBody UserDetails UserDetails) throws IOException {
        if (UserDetails.Email == "" || UserDetails.Password == "" || UserDetails.Username == "") {
            return "Please fill all the fields";
        }
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList = UserDetailsRepo.checkEmailIsExistsAlready(UserDetails.Email);
        if (userDetailsList.size() > 0) {
            return "Email address already Exists";
        } else {
            UserDetailsRepo.insertUser(UserDetails.Email, UserDetails.Password, UserDetails.Username);
            return "Successfully Signed Up!";
        }
    }

    //Check Username exists already in database
    @PostMapping("/checkEmailIdExistsAlready")
    public Boolean checkEmailIdExistsAlready(@RequestBody UserDetails UserDetails) throws IOException {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList = UserDetailsRepo.checkEmailIsExistsAlready(UserDetails.Email);
        return checkDataExistsInUserDetailsList(userDetailsList);
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody UserDetails UserDetails) throws IOException {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList = UserDetailsRepo.login(UserDetails.Email, UserDetails.Password);
        return checkDataExistsInUserDetailsList(userDetailsList);
    }

    private Boolean checkDataExistsInUserDetailsList(List<UserDetails> userDetailsList) {
        if (userDetailsList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
