package com.example.SpringNauka.repositories;

import com.example.SpringNauka.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}