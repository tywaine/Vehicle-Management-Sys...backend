package com.vehiclemanagementsys.vehiclemanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("Sales")
public class Sale {

    @Id
    @Column("saleID")
    private Long id;

    @Column("vehicleID")
    private Long vehicleID;

    @Column("customerID")
    private Long customerID;

    @Column("saleDateTime")
    private LocalDateTime saleDateTime;

    @Column("amountPaid")
    private BigDecimal amountPaid;

    @Column("paymentStatus")
    private String paymentStatus;

    public Sale() {
    }

    public Sale(Long vehicleID, Long customerID, LocalDateTime saleDateTime, BigDecimal amountPaid, String paymentStatus) {
        this.vehicleID = vehicleID;
        this.customerID = customerID;
        this.saleDateTime = saleDateTime;
        this.amountPaid = amountPaid;
        this.paymentStatus = paymentStatus;
    }

    public Sale(Long id, Long vehicleID, Long customerID, LocalDateTime saleDateTime, BigDecimal amountPaid, String paymentStatus) {
        this.id = id;
        this.vehicleID = vehicleID;
        this.customerID = customerID;
        this.saleDateTime = saleDateTime;
        this.amountPaid = amountPaid;
        this.paymentStatus = paymentStatus;
    }

    public Long getID() {
        return id;
    }

    public Long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getSaleDateTime() {
        return saleDateTime;
    }

    public void setSaleDateTime(LocalDateTime saleDateTime) {
        this.saleDateTime = saleDateTime;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", vehicleID=" + vehicleID +
                ", customerID=" + customerID +
                ", saleDateTime=" + saleDateTime +
                ", amountPaid=" + amountPaid +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
