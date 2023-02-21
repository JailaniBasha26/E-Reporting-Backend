package com.rebelskool.implementation;

import com.rebelskool.entity.*;
import com.rebelskool.repo.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rebelskool.implementation.IOExceptio;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OrganizationImpl {

    @Autowired
    private OrganizationRepo OrganizationRepo;

    public Organization getOrganizationDetailsById (String organizationNo) throws IOException{
        Organization organization = new Organization();
        organization = OrganizationRepo.getOrganizationById(organizationNo);
        return organization;
    }


//    public String postOrganizationDetails (Organization organizationParam) throws IOException{
//        Organization organization = new Organization();
//        organization = OrganizationRepo.addNewOrganization(organizationParam.organizationname, organizationParam.organizationno,organizationParam.zipcode,organizationParam.postaladdress);
//        return "Inserted";
//    }
}
