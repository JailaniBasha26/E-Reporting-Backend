package com.project.rebelskool.implementation;

import com.project.rebelskool.entity.*;
import com.project.rebelskool.repo.IncomeStatementFieldsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

        Integer headerCount = 0;
        Map headers=new HashMap();
        String header = new String("");

        for ( IncomeStatementFields result : IncomeStatementFieldsList){
            List<FieldDetails> incomeStatementField = new ArrayList<>();
            FieldDetails fieldsDetails = new FieldDetails();
            fieldsDetails.setName(result.fields);
            fieldsDetails.setRange(result.accountnos);
            fieldsDetails.setYear(result.year);
            fieldsDetails.setAcceptonlynegativevalues(result.acceptonlynegativevalues);
            fieldsDetails.setIssumfield(result.issumfield);

           if(null != headers.get(result.header)){
               header =  headers.get(result.header).toString();
           }
           else
           {
               headerCount++;
               headers.put(result.header, headerCount+"@#%#@"+result.header);
               header = headerCount+"@#%#@"+result.header;
           }

            incomeStatementField = incomeStatementFieldsMap.get(header);
            if (null == incomeStatementField) {
                List<FieldDetails> fieldsDetailsList = new ArrayList<>();
                fieldsDetailsList.add(fieldsDetails);
                incomeStatementFieldsMap.put(header, fieldsDetailsList);
            } else
            {
                incomeStatementField.add(fieldsDetails);
                incomeStatementFieldsMap.put(header, incomeStatementField);
            }
        }

        Map<String,List<FieldDetails>> result = incomeStatementFieldsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return result;
    }

}
