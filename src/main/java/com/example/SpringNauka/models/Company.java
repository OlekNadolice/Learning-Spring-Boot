package com.example.SpringNauka.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;



@Entity
@Table(name = "companies")
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
   private List<Employee> employees;



}
