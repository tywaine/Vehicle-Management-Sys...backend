package com.vehiclemanagementsys.vehiclemanagement.repository;


import com.vehiclemanagementsys.vehiclemanagement.model.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {
}
