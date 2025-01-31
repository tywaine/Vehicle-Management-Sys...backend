package com.vehiclemanagementsys.vehiclemanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("Vehicles")
public class Vehicle {

    @Id
    @Column("vehicleID")
    private Long id;

    @Column("make")
    private String make;

    @Column("model")
    private String model;

    @Column("year")
    private Integer year;

    @Column("color")
    private String color;

    @Column("price")
    private BigDecimal price;

    @Column("mileage")
    private Integer mileage;

    @Column("vehicle_condition")
    private String vehicle_condition;

    @Column("status")
    private String status;

    @Column("acquisitionDateTime")
    private LocalDateTime acquisitionDateTime;

    @Column("supplierID")
    private Long supplierID;

    @Column("soldDateTime")
    private LocalDateTime soldDateTime;

    @Column("createdAt")
    private LocalDateTime createdAt;

    public Vehicle() {
    }

    public Vehicle(String make, String model, Integer year, String color, BigDecimal price, Integer mileage,
                   String vehicle_condition, String status, LocalDateTime acquisitionDateTime, Long supplierID,
                   LocalDateTime soldDateTime, LocalDateTime createdAt) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.mileage = mileage;
        this.vehicle_condition = vehicle_condition;
        this.status = status;
        this.acquisitionDateTime = acquisitionDateTime;
        this.supplierID = supplierID;
        this.soldDateTime = soldDateTime;
        this.createdAt = createdAt;
    }

    public Vehicle(Long id, String make, String model, Integer year, String color, BigDecimal price, Integer mileage,
                   String vehicle_condition, String status, LocalDateTime acquisitionDateTime, Long supplierID,
                   LocalDateTime soldDateTime, LocalDateTime createdAt) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
        this.mileage = mileage;
        this.vehicle_condition = vehicle_condition;
        this.status = status;
        this.acquisitionDateTime = acquisitionDateTime;
        this.supplierID = supplierID;
        this.soldDateTime = soldDateTime;
        this.createdAt = createdAt;
    }

    public Long getID() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getVehicle_condition() {
        return vehicle_condition;
    }

    public void setVehicle_condition(String vehicle_condition) {
        this.vehicle_condition = vehicle_condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAcquisitionDateTime() {
        return acquisitionDateTime;
    }

    public void setAcquisitionDateTime(LocalDateTime acquisitionDateTime) {
        this.acquisitionDateTime = acquisitionDateTime;
    }

    public Long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
    }

    public LocalDateTime getSoldDateTime() {
        return soldDateTime;
    }

    public void setSoldDateTime(LocalDateTime soldDateTime) {
        this.soldDateTime = soldDateTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", mileage=" + mileage +
                ", vehicle_condition='" + vehicle_condition + '\'' +
                ", status='" + status + '\'' +
                ", acquisitionDateTime=" + acquisitionDateTime +
                ", supplierID=" + supplierID +
                ", soldDateTime=" + soldDateTime +
                ", createdAt=" + createdAt +
                '}';
    }
}
