package com.project.rebelskool.implementation;
import com.project.rebelskool.entity.*;
import com.project.rebelskool.repo.OrganizationRepo;
import com.project.rebelskool.repo.IncomeStatementFieldsRepo;
import com.project.rebelskool.repo.IncomeStatementHeadersRepo;
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
public class IncomeStatementImpl {

    @Autowired
    private IncomeStatementFieldsRepo IncomeStatementFieldsRepo;

    public List<HeaderDetails> getIcomeStatementDetails(List<HeaderDetails> HeaderDetails) throws IOExceptio{
        if (HeaderDetails.size() > 0 ) {
            HeaderDetails.forEach(
                    (temp) -> {
                        System.out.println(temp);
                       // temp.organizationname = temp.organizationname + " - INDIA";
                    });
        }
        return HeaderDetails;
    }

    public Map<String,List<FieldDetails>> getIcomeStatementHeaderDetails() throws IOExceptio{
        List<IncomeStatementFields> IncomeStatementFieldsList = new ArrayList<>();
        IncomeStatementFieldsList = IncomeStatementFieldsRepo.findAll();
        Map<String,List<FieldDetails>> incomeStatementFieldsMap = new HashMap<>();

        if (IncomeStatementFieldsList.size() > 0 ) {
            IncomeStatementFieldsList.forEach(
                    (result) -> {
                        List<FieldDetails> incomeStatementField = new ArrayList<>();
                        FieldDetails fieldsDetails = new FieldDetails();
                        fieldsDetails.setName(result.fields);
                        fieldsDetails.setRange(result.accountnos);
                        fieldsDetails.setYear(result.year);

                        incomeStatementField = incomeStatementFieldsMap.get(result.header);
                            if (null == incomeStatementField) {
                                    List<FieldDetails> fieldsDetailsList = new ArrayList<>();
                                    fieldsDetailsList.add(fieldsDetails);
                                incomeStatementFieldsMap.put(result.header, fieldsDetailsList);
                            } else
                                {
                                    incomeStatementField.add(fieldsDetails);
                                    incomeStatementFieldsMap.put(result.header, incomeStatementField);
                                }
                    });
        }
        return incomeStatementFieldsMap;
    }
}
