package com.vehiclemanagementsys.vehiclemanagement.repository;

import com.vehiclemanagementsys.vehiclemanagement.model.Customer;
import com.vehiclemanagementsys.vehiclemanagement.model.Supplier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    @Query("SELECT * FROM Suppliers WHERE email = :email")
    Optional<Supplier> findByEmail(@Param("email") String email);

    @Query("SELECT * FROM Suppliers WHERE phoneNumber = :phoneNumber")
    Optional<Supplier> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
