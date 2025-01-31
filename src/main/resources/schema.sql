USE vehicleManagement;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS Sales;
DROP TABLE IF EXISTS Vehicles;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Suppliers;
DROP TABLE IF EXISTS Users;

-- Create Tables
CREATE TABLE IF NOT EXISTS Users (
                       userID BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100) UNIQUE NOT NULL,
                       passwordHash VARCHAR(255) NOT NULL,
                       role ENUM('Admin', 'Staff') DEFAULT 'Staff',
                       createdAt DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Customers (
                           customerID BIGINT AUTO_INCREMENT PRIMARY KEY,
                           firstName VARCHAR(50) NOT NULL,
                           lastName VARCHAR(50) NOT NULL,
                           phoneNumber VARCHAR(17) UNIQUE NOT NULL,
                           email VARCHAR(100) UNIQUE NOT NULL,
                           address TEXT NULL,
                           createdAt DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Suppliers (
                           supplierID BIGINT AUTO_INCREMENT PRIMARY KEY,
                           firstName VARCHAR(50) NOT NULL,
                           lastName VARCHAR(50) NOT NULL,
                           phoneNumber VARCHAR(17) NOT NULL UNIQUE,
                           email VARCHAR(100) NOT NULL UNIQUE,
                           location TEXT NULL,
                           createdAt DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Vehicles (
                        vehicleID BIGINT AUTO_INCREMENT PRIMARY KEY,
                        make VARCHAR(50) NOT NULL,
                        model VARCHAR(50) NOT NULL,
                        year INT NOT NULL,
                        color VARCHAR(50) NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,
                        mileage INT NULL,
                        vehicle_condition ENUM('New', 'Used') DEFAULT 'Used',
                        status ENUM('Available', 'Sold') DEFAULT 'Available',
                        acquisitionDateTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        supplierID BIGINT NULL,
                        soldDateTime DATETIME NULL DEFAULT NULL,
                        createdAt DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        FOREIGN KEY (supplierID) REFERENCES Suppliers(supplierID)
);

CREATE TABLE IF NOT EXISTS Sales (
                       saleID BIGINT AUTO_INCREMENT PRIMARY KEY,
                       vehicleID BIGINT NOT NULL,
                       customerID BIGINT NOT NULL,
                       saleDateTime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       amountPaid DECIMAL(10, 2) NOT NULL,
                       paymentStatus ENUM('Paid', 'Pending') DEFAULT 'Pending',
                       FOREIGN KEY (vehicleID) REFERENCES Vehicles(vehicleID),
                       FOREIGN KEY (customerID) REFERENCES Customers(customerID)
);