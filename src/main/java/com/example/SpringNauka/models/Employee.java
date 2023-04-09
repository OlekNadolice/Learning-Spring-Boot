package com.example.SpringNauka.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @OneToOne
    @JoinColumn(name = "address_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
