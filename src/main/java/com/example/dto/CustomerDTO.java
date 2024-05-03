package com.example.dto;

import com.example.domain.Customer;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotNull(message = "First name cannot be null!")
    @NotEmpty(message = "First name cannot be empty!")
    @NotBlank(message = "First name cannot be space.")
    private String name;
    @NotNull(message = "First name cannot be null!")
    @NotEmpty(message = "First name cannot be empty!")
    @NotBlank(message = "First name cannot be space.")
    @Size(min = 2, max = 50)
    private String lastName;
    @Email(message="This email is not valid!")
    @Column(unique = false,nullable = false)
    private String email;
    private String phone;

    public CustomerDTO(Customer customer) {
        this.setName(customer.getName());
        this.setLastName(customer.getLastName());
        this.setEmail(customer.getEmail());
        this.setPhone(customer.getPhone());
    }
}
