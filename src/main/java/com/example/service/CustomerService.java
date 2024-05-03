package com.example.service;

import com.example.domain.Customer;
import com.example.dto.CustomerDTO;
import com.example.exception.ConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private customerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ConflictException("Email already exists in DB");
        }
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer with given id is not found"));
    }

    public CustomerDTO getCustomerDTOById(Long id) {
        Customer customer = getCustomerById(id);
        /*
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;
        */
        return new CustomerDTO(customer);
    }

    public void updateCustomer(Long id, CustomerDTO customerDTO) {

        boolean existsEmail = customerRepository.existsByEmail(customerDTO.getEmail());

        Customer customer = getCustomerById(id);

        if (existsEmail && !customer.getEmail().equals(customerDTO.getEmail())) {
            throw new ConflictException("Email already exists!");
        }

        customer.setName(customerDTO.getName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customerRepository.save(customer);

    }
}
