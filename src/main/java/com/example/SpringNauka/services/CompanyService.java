package com.example.SpringNauka.services;

import com.example.SpringNauka.dtos.AddEmployeeToCompanyDTO;
import com.example.SpringNauka.dtos.CreateCompanyDTO;
import com.example.SpringNauka.models.Address;
import com.example.SpringNauka.models.Company;
import com.example.SpringNauka.models.Employee;
import com.example.SpringNauka.repositories.AddressRepository;
import com.example.SpringNauka.repositories.CompanyRepository;
import com.example.SpringNauka.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CompanyService {


    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private  final EmployeeRepository employeeRepository;

    @Autowired
    private  final AddressRepository addressRepository;

    public List<Company> getCompanies() {
        return this.companyRepository.findAll();
    }


    public Optional<Company> getCompanyById(Long companyId) {
        return this.companyRepository.findById(companyId);

    }


    public void createCompany(CreateCompanyDTO company) {
       Company cp = new Company();
       cp.setName(company.getCompanyName());
       this.companyRepository.save(cp);

    }


    public void removeCompany(Long companyId) {
        this.companyRepository.deleteById(companyId);
    }

    public List<Employee> getEmployeesForCompany(Long companyId) {
      return  this.companyRepository.findAllEmployeesForCompany(companyId);

    }

    public void addEmployeeToCompany(Long companyId,  AddEmployeeToCompanyDTO employee) {
        var company = this.companyRepository.findById(companyId);
        company.ifPresent(c -> {
            Employee e = new Employee();
            Address address = new Address();
            address.setCity(employee.getAddress());
            e.setFirstName(employee.getFirstName());
            e.setLastName(employee.getLastName());
            e.setCompany(c);
            e.setAddress(address);
            List<Employee> employees = c.getEmployees();
            employees.add(e);
            c.setEmployees(employees);


     this.companyRepository.save(c);
        });

    }

    public void removeEmployeeFromCompany(Long employeeId) {
        this.employeeRepository.deleteById(employeeId);
    }
}
