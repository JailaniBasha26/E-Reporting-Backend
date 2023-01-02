package com.project.rebelskool.repo;
import com.project.rebelskool.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.rebelskool.entity.IncomeStatementFields;

import java.util.List;

public interface IncomeStatementFieldsRepo extends JpaRepository<IncomeStatementFields, Integer>{
}
