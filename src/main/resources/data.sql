-- Insert data into Users
INSERT INTO Users (username, passwordHash, role)
VALUES
    ('admin1', '$2a$12$7mEVcqvFpFkOPBKpZmNaX.7Z.txBIZDB/.mDe4AmZziRrfpWxh3Cq', 'Admin'),
    ('staff1', '$2a$12$/YTKoIJ2ky2.DslSNg73tON793DWt9bTjNSYfFXZCKoIfvzraBzvO', 'Staff');

-- Insert data into Customers
INSERT INTO Customers (firstName, lastName, phoneNumber, email, address)
VALUES
    ('John', 'Doe', '1234567890', 'john.doe@example.com', '123 Main St'),
    ('Jane', 'Smith', '9876543210', 'jane.smith@example.com', '456 Elm St');

-- Insert data into Suppliers
INSERT INTO Suppliers (firstName, lastName, phoneNumber, email, location)
VALUES
    ('Alice', 'Johnson', '1112223333', 'alice.johnson@example.com', 'Warehouse A'),
    ('Bob', 'Williams', '4445556666', 'bob.williams@example.com', 'Warehouse B');

-- Insert data into Vehicles
INSERT INTO Vehicles (make, model, year, color, price, mileage, vehicle_condition, status, supplierID)
VALUES
    ('Toyota', 'Corolla', 2020, 'red', 20000.00, 15000, 'Used', 'Available', 1),
    ('Honda', 'Civic', 2022, 'yellow', 25000.00, NULL, 'New', 'Available', 2);

-- Insert data into Sales
INSERT INTO Sales (vehicleID, customerID, amountPaid, paymentStatus)
VALUES
    (1, 1, 20000.00, 'Paid'),
    (2, 2, 25000.00, 'Pending');
