package com.vehiclemanagementsys.vehiclemanagement.service;

import com.vehiclemanagementsys.vehiclemanagement.model.Customer;
import com.vehiclemanagementsys.vehiclemanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerByPhoneNumber = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
        Optional<Customer> customerByEmail = customerRepository.findByEmail(customer.getEmail());

        if (customerByPhoneNumber.isPresent() || customerByEmail.isPresent()) {
            throw new IllegalArgumentException("Customer already exists");
        }

        customerRepository.save(customer);
        System.out.println(customer);
    }

    // Get all customers
    public List<Customer> getCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Get customer by email
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customerByEmail = customerRepository.findByEmail(email);

        if(customerByEmail.isEmpty()) {
            throw new IllegalArgumentException("Customer with email " + email + " does not exist");
        }

        return customerByEmail.get();
    }

    // Get customer by phoneNumber
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        Optional<Customer> customerByPhoneNumber = customerRepository.findByPhoneNumber(phoneNumber);

        if(customerByPhoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Customer with email " + phoneNumber + " does not exist");
        }

        return customerByPhoneNumber.get();
    }

    @Transactional
    public void updateCustomer(Long customerId, String firstName, String lastName, String phoneNumber, String email, String address) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + customerId + " does not exist"));

        // Check if the phone number is changing
        if (phoneNumber != null && !phoneNumber.equals(customer.getPhoneNumber())) {
            Optional<Customer> customerByPhoneNumber = customerRepository.findByPhoneNumber(phoneNumber);
            if (customerByPhoneNumber.isPresent()) {
                throw new IllegalArgumentException("Phone number already in use by another customer");
            }
        }

        // Check if the email is changing
        if (email != null && !email.equals(customer.getEmail())) {
            Optional<Customer> customerByEmail = customerRepository.findByEmail(email);
            if (customerByEmail.isPresent()) {
                throw new IllegalArgumentException("Email already in use by another customer");
            }
        }

        if(phoneNumber != null && !phoneNumber.equals(customer.getPhoneNumber())) {
            customer.setPhoneNumber(phoneNumber);
        }

        if(email != null && !email.equals(customer.getEmail())) {
            customer.setEmail(email);
        }

        if(firstName != null && !firstName.equals(customer.getFirstName())) {
            customer.setFirstName(firstName);
        }

        if(lastName != null && !lastName.equals(customer.getLastName())) {
            customer.setLastName(lastName);
        }

        if(address != null && !address.equals(customer.getAddress())) {
            customer.setAddress(address);
        }

        customerRepository.save(customer);
    }


    // Delete customer by ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

