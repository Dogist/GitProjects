
CREATE TABLE cart (
    id  INTEGER PRIMARY KEY,
    account INTEGER,
    totalPrice INTEGER
);

CREATE TABLE cartToOrder (
    id  INTEGER REFERENCES cart(id),
    orderId INTEGER,
    PRIMARY KEY(id, orderId)
);