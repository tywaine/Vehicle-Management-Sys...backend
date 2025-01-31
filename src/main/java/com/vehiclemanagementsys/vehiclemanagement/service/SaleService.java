package com.vehiclemanagementsys.vehiclemanagement.service;

import com.vehiclemanagementsys.vehiclemanagement.model.Sale;
import com.vehiclemanagementsys.vehiclemanagement.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void addNewSale(Sale sale) {
        saleRepository.save(sale);
        System.out.println(sale);
    }

    // Get all sales
    public List<Sale> getSales() {
        return (List<Sale>) saleRepository.findAll();
    }

    // Get Sale by ID
    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    @Transactional
    public void updateSale(Long saleId, Long vehicleId, Long customerId, LocalDateTime saleDateTime, BigDecimal amountPaid, String paymentStatus) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Sale with id " + saleId + " does not exist"));

        if(vehicleId != null && !vehicleId.equals(sale.getVehicleID())) {
            sale.setVehicleID(vehicleId);
        }

        if(customerId != null && !customerId.equals(sale.getCustomerID())) {
            sale.setCustomerID(customerId);
        }

        if(saleDateTime != null && !saleDateTime.equals(sale.getSaleDateTime())) {
            sale.setSaleDateTime(saleDateTime);
        }

        if(amountPaid != null && !amountPaid.equals(sale.getAmountPaid())) {
            sale.setAmountPaid(amountPaid);
        }

        if(paymentStatus != null && !paymentStatus.equals(sale.getPaymentStatus())) {
            sale.setPaymentStatus(paymentStatus);
        }

        saleRepository.save(sale);
    }

    // Delete customer by ID
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

}

