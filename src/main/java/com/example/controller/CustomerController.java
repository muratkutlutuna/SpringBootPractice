package com.example.controller;

import com.example.domain.Customer;
import com.example.dto.CustomerDTO;
import com.example.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")   //default path
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 1- Greet the Spring Boot! ^^ -> http://localhost:8080/customers/greet. --> Print one of them: Hi There!, Hello!, Greetings!, etc.
    @GetMapping("/greet")
    public String/*ResponseEntity<String>*/ printGreetToSpringBoot(){
        //return ResponseEntity.ok("Greetings!");
//        return new ResponseEntity<>("Hi There!", HttpStatus.CREATED);
        return "Hi There!";
    }

    // 2- Make a path that gets the needed info and saves a customer!
//    @PostMapping("/save")
//    public ResponseEntity<Map<String, String>> save(@Valid @RequestBody Customer customer){
//        customerService.saveCustomer(customer);
//        Map<String,String>map = new HashMap<>();
//        map.put("message","Customer is created successfully");
//        map.put("status","true");
//        return new ResponseEntity<>(map, HttpStatus.CREATED);
//
//    }
    @PostMapping("/save")   //http://localhost:8080/customers/save --> Request Body
    public ResponseEntity<String> save(@Valid @RequestBody Customer customer){
        customerService.saveCustomer(customer);
        String message="Customer is created successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    // 3- Get All Customers
    @GetMapping //http://localhost:8080/customers
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer>customers = customerService.getAllCustomers();
//        return new ResponseEntity<>(customers, HttpStatus.OK);
        return ResponseEntity.ok(customers);//HttpStatus: 200 -> OK

    }

    // 4- Get only one customer with an ID. --> //http://localhost:8080/customers/1 --> Path Variable
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable/*("id")*/ Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    //dto data transfer object
    //get a customer by their id. http://localhost:8080/custom?id=1 --> Request Param
    @GetMapping("/custom")
    public ResponseEntity<CustomerDTO>getCustomerDTOById(@RequestParam("id") Long id){
        CustomerDTO customerDTO = customerService.getCustomerDTOById(id);
        return ResponseEntity.ok(customerDTO);
    }

    // 6- Update the customer
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomerById(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok("Customer has been saved successfully.");
    }

}
