package com.vehiclemanagementsys.vehiclemanagement.service;

import com.vehiclemanagementsys.vehiclemanagement.model.Vehicle;
import com.vehiclemanagementsys.vehiclemanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void addNewVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        System.out.println(vehicle);
    }

    // Get all Vehicles
    public List<Vehicle> getVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    // Get Vehicle by ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Transactional
    public void updateVehicle(Long vehicleID, String make, String model, Integer year, String color, BigDecimal price,
                              Integer mileage, String vehicle_condition, String status,
                               LocalDateTime acquisitionDateTime, Long supplierID, LocalDateTime soldDateTime) {
        Vehicle vehicle = vehicleRepository.findById(vehicleID)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with id " + vehicleID + " does not exist"));

        if(make != null && !make.equals(vehicle.getMake())) {
            vehicle.setMake(make);
        }

        if(model != null && !model.equals(vehicle.getModel())) {
            vehicle.setModel(model);
        }

        if(year != null && !year.equals(vehicle.getYear())) {
            vehicle.setYear(year);
        }

        if(color != null && !color.equals(vehicle.getColor())) {
            vehicle.setColor(color);
        }

        if(price != null && !price.equals(vehicle.getPrice())) {
            vehicle.setPrice(price);
        }

        if(mileage != null && !mileage.equals(vehicle.getMileage())) {
            vehicle.setMileage(mileage);
        }

        if(vehicle_condition != null && !vehicle_condition.equals(vehicle.getVehicle_condition())) {
            vehicle.setVehicle_condition(vehicle_condition);
        }

        if(status != null && !status.equals(vehicle.getStatus())) {
            vehicle.setStatus(status);
        }

        if(acquisitionDateTime != null && !acquisitionDateTime.equals(vehicle.getAcquisitionDateTime())) {
            vehicle.setAcquisitionDateTime(acquisitionDateTime);
        }

        if(supplierID != null && !supplierID.equals(vehicle.getSupplierID())) {
            vehicle.setSupplierID(supplierID);
        }

        if(soldDateTime != null && !soldDateTime.equals(vehicle.getSoldDateTime())) {
            vehicle.setSoldDateTime(soldDateTime);
        }

        vehicleRepository.save(vehicle);
    }


    // Delete Vehicle by ID
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

}
