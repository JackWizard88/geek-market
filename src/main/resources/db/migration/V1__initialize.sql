create table customers (
    id                      bigint primary key AUTO_INCREMENT,
    name                    varchar(255) not null
);

create table products (
    id                      bigint primary key AUTO_INCREMENT,
    title                   varchar(255),
    price                   int
);

create table orders (
    id                      bigint primary key AUTO_INCREMENT,
    customer_id             bigint references customers(id),
    product_id              bigint references products(id),
    deal_price              decimal(10,2)
);

insert into customers (name)
values
('John'),
('Tom'),
('Billy'),
('Edd'),
('Bob'),
('Jack');

insert into products (title, price)
values
('Bread', 25.50),
('Sausages', 225.50),
('Yogurt', 85.50),
('Cream', 250.90),
('Carrot', 35.00),
('Onion', 45.50),
('Garlic', 25.50),
('Cabbage', 30.90),
('Butter', 120.99),
('Coffee', 450.90),
('Cheese', 650),
('Tea', 250.80),
('Milk', 79.90),
('Tomatoes', 150.99),
('Cucumbers', 89.90),
('Potatoes', 49.00),
('Eggs', 130.49);


insert into orders (customer_id, product_id, deal_price)
values
(1, 1, 24),
(1, 2, 79.90),
(1, 3, 45.50),
(2, 3, 85.50),
(2, 4, 79.90),
(2, 5, 45.50),
(2, 6, 85.50),
(3, 8, 45.50),
(3, 7, 85.50),
(4, 10, 45.50),
(4, 12, 85.50),
(4, 13, 250.80),
(4, 12, 45.50),
(5, 11, 250.80),
(5, 10, 45.50),
(5, 7, 45.50),
(5, 3, 79.90);