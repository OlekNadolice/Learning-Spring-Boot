package com.example.SpringNauka.repositories;

import com.example.SpringNauka.models.Company;
import com.example.SpringNauka.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT u FROM Employee u WHERE  u.company.id = :companyId ")
    List<Employee> findAllEmployeesForCompany(@Param("companyId") Long companyId);
}