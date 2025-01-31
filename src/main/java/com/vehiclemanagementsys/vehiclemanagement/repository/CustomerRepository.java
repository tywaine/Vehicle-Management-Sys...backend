package com.vehiclemanagementsys.vehiclemanagement.repository;

import com.vehiclemanagementsys.vehiclemanagement.model.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT * FROM Customers WHERE email = :email")
    Optional<Customer> findByEmail(@Param("email") String email);

    @Query("SELECT * FROM Customers WHERE phoneNumber = :phoneNumber")
    Optional<Customer> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
