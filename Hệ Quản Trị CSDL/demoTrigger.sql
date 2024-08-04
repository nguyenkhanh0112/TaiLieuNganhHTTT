
create database demoTrigger1;
use demoTrigger1;


-- Tạo bảng Customers
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    CustomerName NVARCHAR(50),
    Email NVARCHAR(50),
    OrderCount INT DEFAULT 0
);

-- Tạo bảng Orders với khoá ngoại liên quan đến Customers
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATE,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);


-- Thêm dữ liệu vào bảng Customers
INSERT INTO Customers (CustomerID, CustomerName, Email)
VALUES
    (1, 'hoangtien2k3', 'hoangtien2k3@gxample.com'),
    (2, 'tiendaica123', 'tiendaica123@gxample.com'),
    (3, 'trumdatbac123', 'trumdatbac123@gxample.com');



-- Thêm dữ liệu vào bảng Orders
INSERT INTO Orders (OrderID, CustomerID, OrderDate, TotalAmount)
VALUES
    (101, 1, '2023-01-15', 150.00),
    (102, 2, '2023-02-20', 200.50),
    (103, 1, '2023-03-10', 100.25),
    (104, 3, '2023-04-05', 300.75);




-- Tạo trigger để tự động cập nhật thông tin số lượng đơn hàng của mỗi khách hàng trong bảng Customers khi có đơn hàng mới
CREATE TRIGGER trgUpdateCustomerOrderCount
ON Orders
AFTER INSERT
AS
BEGIN
    UPDATE Customers
    SET OrderCount = (
        SELECT COUNT(*) FROM Orders WHERE Orders.CustomerID = Customers.CustomerID
    )
    FROM Customers;
END;




-- Thêm dữ liệu vào bảng Orders
INSERT INTO Orders (OrderID, CustomerID, OrderDate, TotalAmount)
VALUES
    (109, 1, '2023-05-15', 120.00),
    (1010, 3, '2023-06-20', 180.50);


-- Truy vấn dữ liệu từ bảng Customers để xem kết quả của trigger
SELECT * FROM Customers;




-- Tạo trigger để tự động cập nhật OrderCount trong bảng Customers khi có đơn hàng bị xóa
CREATE TRIGGER trgDeleteOrderUpdateCustomerOrderCount
ON Orders
AFTER DELETE
AS
BEGIN
    UPDATE Customers
    SET OrderCount = (
        SELECT COUNT(*) FROM Orders WHERE Orders.CustomerID = Customers.CustomerID
    )
    FROM Customers;
END;




-- Xóa đơn hàng có OrderID là 105
DELETE FROM Orders WHERE OrderID = 108;

-- Truy vấn dữ liệu từ bảng Customers để xem kết quả của trigger
SELECT * FROM Customers;

SELECT * FROM Orders;





