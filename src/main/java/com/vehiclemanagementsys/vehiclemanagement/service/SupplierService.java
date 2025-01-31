package com.vehiclemanagementsys.vehiclemanagement.service;

import com.vehiclemanagementsys.vehiclemanagement.model.Supplier;
import com.vehiclemanagementsys.vehiclemanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public void addNewSupplier(Supplier supplier) {
        Optional<Supplier> customerByPhoneNumber = supplierRepository.findByPhoneNumber(supplier.getPhoneNumber());
        Optional<Supplier> customerByEmail = supplierRepository.findByEmail(supplier.getEmail());

        if (customerByPhoneNumber.isPresent() || customerByEmail.isPresent()) {
            throw new IllegalArgumentException("Supplier already exists");
        }

        supplierRepository.save(supplier);
        System.out.println(supplier);
    }

    // Get all customers
    public List<Supplier> getSuppliers() {
        return (List<Supplier>) supplierRepository.findAll();
    }

    // Get supplier by ID
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    // Get supplier by email
    public Supplier getSupplierByEmail(String email) {
        Optional<Supplier> supplierByEmail = supplierRepository.findByEmail(email);

        if(supplierByEmail.isEmpty()) {
            throw new IllegalArgumentException("Supplier with email " + email + " does not exist");
        }

        return supplierByEmail.get();
    }

    // Get Supplier by phoneNumber
    public Supplier getSupplierByPhoneNumber(String phoneNumber) {
        Optional<Supplier> supplierByPhoneNumber = supplierRepository.findByPhoneNumber(phoneNumber);

        if(supplierByPhoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Supplier with email " + phoneNumber + " does not exist");
        }

        return supplierByPhoneNumber.get();
    }

    @Transactional
    public void updateSupplier(Long supplierID, String firstName, String lastName, String phoneNumber, String email, String location) {
        Supplier supplier = supplierRepository.findById(supplierID)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + supplierID + " does not exist"));

        // Check if the phone number is changing
        if (phoneNumber != null && !phoneNumber.equals(supplier.getPhoneNumber())) {
            Optional<Supplier> supplierByPhoneNumber = supplierRepository.findByPhoneNumber(phoneNumber);
            if (supplierByPhoneNumber.isPresent()) {
                throw new IllegalArgumentException("Phone number already in use by another customer");
            }
        }

        // Check if the email is changing
        if (email != null && !email.equals(supplier.getEmail())) {
            Optional<Supplier> supplierByEmail = supplierRepository.findByEmail(email);
            if (supplierByEmail.isPresent()) {
                throw new IllegalArgumentException("Email already in use by another customer");
            }
        }

        if(phoneNumber != null && !phoneNumber.equals(supplier.getPhoneNumber())) {
            supplier.setPhoneNumber(phoneNumber);
        }

        if(email != null && !email.equals(supplier.getEmail())) {
            supplier.setEmail(email);
        }

        if(firstName != null && !firstName.equals(supplier.getFirstName())) {
            supplier.setFirstName(firstName);
        }

        if(lastName != null && !lastName.equals(supplier.getLastName())) {
            supplier.setLastName(lastName);
        }

        if(location != null && !location.equals(supplier.getLocation())) {
            supplier.setLocation(location);
        }

        supplierRepository.save(supplier);
    }


    // Delete supplier by ID
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }


}
