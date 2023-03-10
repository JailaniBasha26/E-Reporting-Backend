package com.rebelskool.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rebelskool.entity.IncomeStatementFields;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeStatementFieldsRepo extends JpaRepository<IncomeStatementFields, Integer>{
    @Query(value = "select * from incomestatementfields where year=?1", nativeQuery = true)
    List<IncomeStatementFields> getIncomeStatementFieldsByYear(Integer year);

    @Query(value = "select * from incomestatementfields where year IN (?1)", nativeQuery = true)
    List<IncomeStatementFields> getIncomeStatementFieldsByFinancialYears(String[] year);

}
