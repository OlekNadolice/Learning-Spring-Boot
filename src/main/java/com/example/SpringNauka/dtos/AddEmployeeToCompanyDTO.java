package com.example.SpringNauka.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddEmployeeToCompanyDTO {



        @Size(max = 255, message = "Maximum length for first name is 255 characters")
        @NotBlank(message = "First name is required")
        String firstName;


        @Size(max = 255, message = "Maximum length for last name is 255 characters")
        @NotBlank(message = "Last name is required")
        String lastName;

        @NotBlank(message = "Address is required")
        String address;



}
