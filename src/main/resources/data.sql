-- Create the 'customer' table if it does not exist
CREATE TABLE IF NOT EXISTS customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    salary DECIMAL(10, 2),
    address VARCHAR(255),
    age INT,
    active BOOLEAN DEFAULT TRUE
);

-- Insert 10 sample records into the 'customer' table
INSERT INTO customer (name, email, phone, salary, address, age, active)
VALUES
    ('John Doe', 'john.doe@example.com', '1234567890', 50000.00, '123 Elm Street', 30, TRUE),
    ('Jane Smith', 'jane.smith@example.com', '0987654321', 60000.00, '456 Oak Avenue', 28, TRUE),
    ('Alice Johnson', 'alice.johnson@example.com', '1122334455', 45000.00, '789 Pine Road', 35, TRUE),
    ('Bob Brown', 'bob.brown@example.com', '2233445566', 55000.00, '101 Maple Boulevard', 40, TRUE),
    ('Charlie Lee', 'charlie.lee@example.com', '3344556677', 65000.00, '202 Birch Drive', 45, TRUE),
    ('David Clark', 'david.clark@example.com', '4455667788', 70000.00, '303 Cedar Street', 32, TRUE),
    ('Eva Green', 'eva.green@example.com', '5566778899', 48000.00, '404 Redwood Lane', 26, TRUE),
    ('Frank White', 'frank.white@example.com', '6677889900', 59000.00, '505 Fir Avenue', 38, TRUE),
    ('Grace Black', 'grace.black@example.com', '7788990011', 75000.00, '606 Cedar Crescent', 50, TRUE),
    ('Hank Wilson', 'hank.wilson@example.com', '8899001122', 80000.00, '707 Sequoia Parkway', 60, TRUE);

