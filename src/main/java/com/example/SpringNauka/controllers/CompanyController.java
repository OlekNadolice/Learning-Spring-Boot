package com.example.SpringNauka.controllers;

import com.example.SpringNauka.dtos.AddEmployeeToCompanyDTO;
import com.example.SpringNauka.dtos.CreateCompanyDTO;
import com.example.SpringNauka.models.Company;
import com.example.SpringNauka.models.Employee;
import com.example.SpringNauka.services.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping(path = "/companies")
@AllArgsConstructor
public class CompanyController {

    @Autowired
    private final CompanyService companyService;


    @GetMapping()
    public String getCompanies(Model model) {
        var companies = this.companyService.getCompanies();
        model.addAttribute("company", new CreateCompanyDTO());
        model.addAttribute("companies", companies);
        return "Companies";
    }


    @GetMapping("/{companyId}")
    public String getCompanyDetails(@PathVariable Long companyId, Model model) {
        this.companyService.getCompanyById(companyId).
                ifPresentOrElse(c -> {
                            List<Employee> employees = this.companyService.getEmployeesForCompany(companyId);
                            model.addAttribute("company", c);
                            model.addAttribute("employees", employees);
                        },
                        () -> model.addAttribute("companyDoesntExist", true)

                );
        return "Company";
    }

    @PostMapping
    public String createCompany(@Valid @ModelAttribute("company")  CreateCompanyDTO company, BindingResult bindingResult, RedirectAttributes atr, Model model) {
        if (bindingResult.hasErrors()) {
            atr.addFlashAttribute("validationError", true);
            return "redirect:/companies";
        }
        this.companyService.createCompany(company);
        atr.addFlashAttribute("companyCreated", true);
        return "redirect:/companies";
    }

    @PostMapping("/{companyId}")
    public String removeCompany(@PathVariable Long companyId, RedirectAttributes atr) {
        this.companyService.removeCompany(companyId);
        atr.addFlashAttribute("Success", "Company has been removed");
        return "redirect:/companies";
    }


    @PostMapping("/{companyId}/employees")
    public String addEmployeeToCompany(@PathVariable Long companyId, @Valid @ModelAttribute AddEmployeeToCompanyDTO employee, BindingResult bindingResult, RedirectAttributes atr) {
        if (bindingResult.hasErrors()) {
            atr.addFlashAttribute("validationError", true);
            return "redirect:/companies/" + companyId;
        }

        this.companyService.addEmployeeToCompany(companyId, employee);
        atr.addAttribute("addedEmployee", true);
        return "redirect:/companies/" + companyId;
    }

    @PostMapping("/{companyId}/employees/{employeeId}/delete")
    public String removeEmployeeFromCompany(@PathVariable Long companyId, @PathVariable Long employeeId, RedirectAttributes atr) {
        this.companyService.removeEmployeeFromCompany(employeeId);
        atr.addFlashAttribute("removedEmployee", true);
        return "redirect:/companies/" + companyId;
    }


}
