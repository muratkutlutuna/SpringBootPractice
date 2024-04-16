package com.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="t_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotNull //cannot be null
//    @NotEmpty //cannot be null, or have 0 caharacters
//    @NotBlank //cannot be null, cannot be empty, wouldn't accept space character(" ")

    @NotNull(message = "First name cannot be null!")
    @NotBlank(message = "First name cannot be null,  empty, nor space.")
    private String name;
    @NotNull(message = "Last name cannot be null!")
    @NotBlank(message = "Last name cannot be null,  empty, nor space.")
    private String lastName;
    @Column(unique = false,nullable = false)
    private String email;
    private String phone;


}
