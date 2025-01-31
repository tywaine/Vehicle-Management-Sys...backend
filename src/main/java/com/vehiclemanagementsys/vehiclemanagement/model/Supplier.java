package com.vehiclemanagementsys.vehiclemanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("Suppliers")
public class Supplier {

    @Id
    @Column("supplierID")
    private Long id;

    @Column("firstName")
    private String firstName;

    @Column("lastName")
    private String lastName;

    @Column("phoneNumber")
    private String phoneNumber;

    @Column("email")
    private String email;

    @Column("location")
    private String location;

    @Column("createdAt")
    private LocalDateTime createdAt;

    public Supplier() {
    }

    public Supplier(String firstName, String lastName, String phoneNumber, String email, String location, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.location = location;
        this.createdAt = createdAt;
    }

    public Supplier(Long supplierID, String firstName, String lastName, String phoneNumber, String email, String location, LocalDateTime createdAt) {
        this.id = supplierID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.location = location;
        this.createdAt = createdAt;
    }

    public Long getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
