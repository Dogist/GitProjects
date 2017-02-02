CREATE TABLE products (
    id INTEGER IDENTITY PRIMARY KEY,
    name varchar(255),
    number varchar(30)
);

CREATE TABLE accounts (
    id INTEGER IDENTITY PRIMARY KEY,
    number varchar(40),
    owner varchar(40),
    balance decimal(15,2)
);

CREATE TABLE orders (
    id INTEGER IDENTITY PRIMARY KEY,
    name varchar(40),
    number varchar(40),
    price decimal(15,2)
--     pid INTEGER references products(id) NOT NULL,
--     cid INTEGER references carts(id) NOT NULL,
);

-- CREATE TABLE carts (
--     id INTEGER IDENTITY PRIMARY KEY,
--     aid INTEGER references accounts(id) NOT NULL,
-- );