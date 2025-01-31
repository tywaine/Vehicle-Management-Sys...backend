package com.vehiclemanagementsys.vehiclemanagement.controller;

import com.vehiclemanagementsys.vehiclemanagement.model.Supplier;
import com.vehiclemanagementsys.vehiclemanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getSuppliers(@RequestParam(required = false) Long id) {
        if (id != null) {
            Optional<Supplier> supplier = supplierService.getSupplierById(id);

            if (supplier.isPresent()) {
                return List.of(supplier.get());
            }
            else {
                return new ArrayList<>();
            }
        }
        else{
            return supplierService.getSuppliers();
        }
    }

    @PostMapping
    public ResponseEntity<String> registerNewSupplier(@RequestBody Supplier supplier) {
        supplierService.addNewSupplier(supplier);
        return new ResponseEntity<>("Supplier registered successfully", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteSupplier(@RequestParam Long id) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);

        if (supplier.isPresent()) {
            supplierService.deleteSupplier(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{supplierID}")
    public ResponseEntity<String> updateSupplier(
            @PathVariable("supplierID") Long supplierID,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String location) {

        // Ensure at least one field is provided
        if (firstName == null && lastName == null && phoneNumber == null && email == null && location == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        try{
            supplierService.updateSupplier(supplierID, firstName, lastName, phoneNumber, email, location);
            return new ResponseEntity<>("Supplier updated successfully", HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
