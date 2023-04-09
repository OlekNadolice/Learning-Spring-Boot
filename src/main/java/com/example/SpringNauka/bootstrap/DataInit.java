package com.example.SpringNauka.bootstrap;
import com.example.SpringNauka.models.Address;
import com.example.SpringNauka.models.Company;
import com.example.SpringNauka.models.Employee;
import com.example.SpringNauka.repositories.AddressRepository;
import com.example.SpringNauka.repositories.CompanyRepository;
import com.example.SpringNauka.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class DataInit implements CommandLineRunner {

    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private  final EmployeeRepository employeeRepository;

    @Autowired
    private final AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
         Company company = new Company();
         Company company2 = new Company();
         Company company3 = new Company();
         company.setName("Sony");
         company2.setName("Microsoft");
         company3.setName("Tesco");
         this.companyRepository.save(company);
         this.companyRepository.save(company2);
         this.companyRepository.save(company3);
        Employee employee = new Employee();
        employee.setFirstName("Aleksander");
        employee.setLastName("Cygan");
        Address address = new Address();
        address.setCity("Amsterdam");
        employee.setAddress(address);
        employee.setCompany(company);
        this.employeeRepository.save(employee);
        System.out.println("Company has been saved");

    }
}
