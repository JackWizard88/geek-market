create table customers (
    id                      bigint primary key AUTO_INCREMENT,
    name                    varchar(255) not null
);

create table products (
    id                      bigint primary key AUTO_INCREMENT,
    title                   varchar(255),
    price                   decimal(10,2)
);

create table orders (
    id                      bigint primary key AUTO_INCREMENT,
    created                 datetime,
    name                    varchar(255),
    address                 varchar(255),
    phone_number            varchar(255),
    price                   decimal(10,2)
);

create table order_items (
    id                      bigint primary key AUTO_INCREMENT,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   decimal(10,2),
    price_per_product       decimal(10,2),
    quantity                int
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
