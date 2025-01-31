package com.vehiclemanagementsys.vehiclemanagement.controller;

import com.vehiclemanagementsys.vehiclemanagement.model.Sale;
import com.vehiclemanagementsys.vehiclemanagement.service.SaleService;
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
@RequestMapping(path = "api/v1/Sales")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> getSales(@RequestParam(required = false) Long id) {
        if (id != null) {
            Optional<Sale> sale = saleService.getSaleById(id);

            if (sale.isPresent()) {
                return List.of(sale.get());
            } else {
                return new ArrayList<>();
            }
        }

        return saleService.getSales();
    }

    @PostMapping
    public ResponseEntity<String> registerNewSale(@RequestBody Sale sale) {
        saleService.addNewSale(sale);
        return new ResponseEntity<>("Sale added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteSale(@RequestParam Long id) {
        Optional<Sale> sale = saleService.getSaleById(id);

        if (sale.isPresent()) {
            saleService.deleteSale(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{saleID}")
    public ResponseEntity<String> updateSale(
            @PathVariable("saleID") Long saleID,
            @RequestParam(required = false) Long vehicleID,
            @RequestParam(required = false) Long customerID,
            @RequestParam(required = false) LocalDateTime saleDateTime,
            @RequestParam(required = false) BigDecimal amountPaid,
            @RequestParam(required = false) String paymentStatus) {

        // Ensure at least one field is provided
        if (vehicleID == null && customerID == null && saleDateTime == null && amountPaid == null && paymentStatus == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        saleService.updateSale(saleID, vehicleID, customerID, saleDateTime, amountPaid, paymentStatus);
        return new ResponseEntity<>("Sale updated successfully", HttpStatus.OK);
    }

}
