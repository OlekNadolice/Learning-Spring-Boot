package com.example.SpringNauka.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateCompanyDTO {

        @Size(max = 255, message = "Maximum size for company name is 255 characters")
        @NotBlank(message = "Company name is required")
         String companyName;



}
