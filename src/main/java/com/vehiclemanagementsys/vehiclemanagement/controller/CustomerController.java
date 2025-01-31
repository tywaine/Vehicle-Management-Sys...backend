package com.vehiclemanagementsys.vehiclemanagement.controller;

import com.vehiclemanagementsys.vehiclemanagement.model.Customer;
import com.vehiclemanagementsys.vehiclemanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(@RequestParam(required = false) Long id) {
        if (id != null) {
            Optional<Customer> customer = customerService.getCustomerById(id);

            if (customer.isPresent()) {
                return List.of(customer.get());
            } else {
                return new ArrayList<>();
            }
        }

        return customerService.getCustomers();
    }

    @PostMapping
    public ResponseEntity<String> registerNewCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
        return new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteCustomer(@RequestParam Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);

        if (customer.isPresent()) {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{customerID}")
    public ResponseEntity<String> updateCustomer(
            @PathVariable("customerID") Long customerID,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address) {

        // Ensure at least one field is provided
        if (firstName == null && lastName == null && phoneNumber == null && email == null && address == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        try{
            customerService.updateCustomer(customerID, firstName, lastName, phoneNumber, email, address);
            return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

