package com.vehiclemanagementsys.vehiclemanagement.controller;

import com.vehiclemanagementsys.vehiclemanagement.model.Vehicle;
import com.vehiclemanagementsys.vehiclemanagement.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getVehicles(@RequestParam(required = false) Long id) {
        if (id != null) {
            Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);

            if (vehicle.isPresent()) {
                return List.of(vehicle.get());
            } else {
                return new ArrayList<>();
            }
        }

        return vehicleService.getVehicles();
    }

    @PostMapping
    public ResponseEntity<String> registerNewVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addNewVehicle(vehicle);
        return new ResponseEntity<>("Vehicle added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteVehicle(@RequestParam Long id) {
        Optional<Vehicle> sale = vehicleService.getVehicleById(id);

        if (sale.isPresent()) {
            vehicleService.deleteVehicle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{vehicleID}")
    public ResponseEntity<String> updateVehicle(
            @PathVariable("vehicleID") Long vehicleID,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Integer mileage,
            @RequestParam(required = false) String vehicle_condition,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDateTime acquisitionDateTime,
            @RequestParam(required = false) Long supplierID,
            @RequestParam(required = false) LocalDateTime soldDateTime) {

        // Ensure at least one field is provided
        if (vehicleID == null && make == null && model == null && year == null && color == null && price == null && mileage == null
        && vehicle_condition == null && status == null && acquisitionDateTime == null && supplierID == 0 && soldDateTime == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        vehicleService.updateVehicle(vehicleID, make, model, year, color, price, mileage, vehicle_condition, status,
                acquisitionDateTime, supplierID, soldDateTime);
        return new ResponseEntity<>("Vehicle updated successfully", HttpStatus.OK);
    }
}
