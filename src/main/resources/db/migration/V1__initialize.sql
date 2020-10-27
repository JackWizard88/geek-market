create table users (
  id                    bigint primary key AUTO_INCREMENT,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique
);

create table roles (
  id                    bigint primary key AUTO_INCREMENT,
  name                  varchar(50) not null
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               bigint not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('SOMETHING');

insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
('user1', '$2y$12$vRywsljiTPRvi.OdemJlg.mkQKoa/uqUEbDJ93ACB9UF58mdBWXIG', 'user1@gmail.com');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2), (2, 1);

create table products (
    id                      bigint primary key AUTO_INCREMENT,
    title                   varchar(255),
    price                   decimal(10,2)
);

create table orders (
    id                      bigint primary key AUTO_INCREMENT,
    user_id                 bigint references users(id),
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
