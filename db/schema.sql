CREATE TABLE Employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(50),
    contact VARCHAR(50)
);

CREATE TABLE Department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE Payroll (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employeeId INT,
    salary DECIMAL(10,2),
    payDate DATE,
    FOREIGN KEY (employeeId) REFERENCES Employee(id)
);

CREATE TABLE Attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employeeId INT,
    date DATE,
    inTime TIME,
    outTime TIME,
    FOREIGN KEY (employeeId) REFERENCES Employee(id)
);