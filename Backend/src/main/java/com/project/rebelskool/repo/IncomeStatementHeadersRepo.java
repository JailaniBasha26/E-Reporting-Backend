package com.project.rebelskool.repo;
import com.project.rebelskool.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.rebelskool.entity.IncomeStatementHeaders;

import javax.transaction.Transactional;
import java.util.List;

public interface IncomeStatementHeadersRepo extends JpaRepository<IncomeStatementHeaders, String>{
}
