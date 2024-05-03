package com.example.domain;

import com.example.dto.CustomerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotNull //cannot be null
//    @NotEmpty //cannot be null, or have 0 caharacters
//    @NotBlank //cannot be null, cannot be empty, wouldn't accept space character(" ")

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

    public Customer(CustomerDTO customerDTO) {
        this.setName(customerDTO.getName());
        this.setLastName(customerDTO.getLastName());
        this.setEmail(customerDTO.getEmail());
        this.setPhone(customerDTO.getPhone());
    }

}
