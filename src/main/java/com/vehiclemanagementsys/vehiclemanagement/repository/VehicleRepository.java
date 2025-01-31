package com.vehiclemanagementsys.vehiclemanagement.repository;

import com.vehiclemanagementsys.vehiclemanagement.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
