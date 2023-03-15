package com.rebelskool.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rebelskool.entity.Organization;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface OrganizationRepo extends JpaRepository<Organization, String> {
    @Query(value = "select * from organization where organizationnumber=?1", nativeQuery = true)
    Organization getOrganizationById(String organizationNo);

    @Modifying
    @Query(value = "insert into organization values(?1,?2,?3,?4)", nativeQuery = true)
    @Transactional
    void addNewOrganization(String organizationname, String organizationno, Integer postalcode, String city);

}
