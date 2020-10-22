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
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@gmail.com');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2), (2, 1);

create table categories (
    id                      bigint primary key AUTO_INCREMENT,
    title                   varchar(255)
);

create table products (
    id                      bigint primary key AUTO_INCREMENT,
    title                   varchar(255),
    category_id             bigint references categories(id),
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

insert into categories (title)
values
('Молочные продукты'),
('Бакалея'),
('Мясо и мясные продукты'),
('Фрукты'),
('Овощи'),
('Рыба и морепродукты'),
('Напитки'),
('Яйца');

insert into products (title, price, category_id)
values
('Хлеб', 25.50, 2),
('Сосиски', 225.50, 3),
('Йогурт', 85.50, 1),
('Сливки', 250.90, 1),
('Морковь', 35.00, 5),
('Лук', 45.50, 5),
('Чеснок', 25.50, 5),
('Кабачок', 30.90, 5),
('Масло', 120.99, 1),
('Коффе', 450.90, 7),
('Сыр', 650, 1),
('Чай', 250.80, 7),
('Молоко', 79.90, 1),
('Томаты', 150.99, 5),
('Огурцы', 89.90, 5),
('картофель', 49.00, 5),
('Яйца', 130.49, 8),
('Персики', 130.49, 4),
('Бананы', 130.49, 4),
('Яблоки', 130.49, 4);
