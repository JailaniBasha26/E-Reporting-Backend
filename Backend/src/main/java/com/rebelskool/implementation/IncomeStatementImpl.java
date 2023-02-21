package com.rebelskool.implementation;

import com.rebelskool.entity.*;
import com.rebelskool.repo.IncomeStatementFieldsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IncomeStatementImpl {

    @Autowired
    private IncomeStatementFieldsRepo IncomeStatementFieldsRepo;

    public List<HeaderDetails> getIcomeStatementDetails(List<HeaderDetails> HeaderDetails) throws IOException{
        if (HeaderDetails.size() > 0 ) {
            HeaderDetails.forEach(
                    (temp) -> {
                        System.out.println(temp);
                       // temp.organizationname = temp.organizationname + " - INDIA";
                    });
        }
        return HeaderDetails;
    }

    public Map<String,List<FieldDetails>> getIncomeStatementFieldsByFinancialYears(String year) throws IOException {
        List<IncomeStatementFields> IncomeStatementFieldsList = new ArrayList<>();
        String[] financialYearsList = year.split(",");
        IncomeStatementFieldsList = IncomeStatementFieldsRepo.getIncomeStatementFieldsByFinancialYears(financialYearsList);
        Map<String,List<FieldDetails>> incomeStatementFieldsMap = new HashMap<>();
        Map<String, List<FieldDetails>> result = getFieldsResponse(IncomeStatementFieldsList, incomeStatementFieldsMap);
        return result;
    }


    public Map<String,List<FieldDetails>> getIncomeStatementFieldsByYear(Integer year) throws IOException {
        List<IncomeStatementFields> IncomeStatementFieldsList = new ArrayList<>();
        IncomeStatementFieldsList = IncomeStatementFieldsRepo.getIncomeStatementFieldsByYear(year);
        Map<String,List<FieldDetails>> incomeStatementFieldsMap = new HashMap<>();

        Map<String, List<FieldDetails>> result = getFieldsResponse(IncomeStatementFieldsList, incomeStatementFieldsMap);
        return result;
    }

    private Map<String, List<FieldDetails>> getFieldsResponse(List<IncomeStatementFields> incomeStatementFieldsList, Map<String, List<FieldDetails>> incomeStatementFieldsMap) {
        Integer headerCount = 0;
        Map headers = new HashMap();
        String header = new String("");

        for (IncomeStatementFields result : incomeStatementFieldsList) {
            List<FieldDetails> incomeStatementField = new ArrayList<>();
            FieldDetails fieldsDetails = new FieldDetails();
            fieldsDetails.setName(result.fields);
            fieldsDetails.setRange(result.accountnos);
            fieldsDetails.setYear(result.year.toString());
            fieldsDetails.setAcceptonlynegativevalues(result.acceptonlynegativevalues);
            fieldsDetails.setIssumfield(result.issumfield);

            if (null != headers.get(result.header)) {
                header = headers.get(result.header).toString();
            } else {
                headerCount++;
                header = headerCount + "@#%#@" + result.header;
                headers.put(result.header, header);
            }

            incomeStatementField = incomeStatementFieldsMap.get(header);
            if (null == incomeStatementField) {
                List<FieldDetails> fieldsDetailsList = new ArrayList<>();
                fieldsDetailsList.add(fieldsDetails);
                incomeStatementFieldsMap.put(header, fieldsDetailsList);
            } else {
                FieldDetails tempFieldDetails = new FieldDetails();
                for (FieldDetails fieldDetail : incomeStatementField) {
                    if (fieldDetail.getName().contains(fieldsDetails.getName())){
                        tempFieldDetails = fieldDetail;
                        fieldsDetails.setYear(fieldDetail.getYear()+","+fieldsDetails.getYear());
                    }
                }

                incomeStatementField.remove(tempFieldDetails);
                incomeStatementField.add(fieldsDetails);
                incomeStatementFieldsMap.put(header, incomeStatementField);
            }
        }

        return incomeStatementFieldsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public Map<String,List<FieldDetails>> getIcomeStatementHeaderDetails() throws IOException {
        List<IncomeStatementFields> IncomeStatementFieldsList = new ArrayList<>();
        IncomeStatementFieldsList = IncomeStatementFieldsRepo.findAll();
        Map<String,List<FieldDetails>> incomeStatementFieldsMap = new HashMap<>();

        Map<String, List<FieldDetails>> result = getFieldsResponse(IncomeStatementFieldsList, incomeStatementFieldsMap);
        return result;
    }

}
