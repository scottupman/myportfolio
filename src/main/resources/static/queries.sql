CREATE TABLE assets
(
    username TEXT,
    symbol TEXT,
    name TEXT,
    type TEXT,
    quantity INTEGER,
    PRIMARY KEY(username, symbol),
    FOREIGN KEY(username) REFERENCES users(username)
);

CREATE TABLE trades
(
    trade_id SERIAL PRIMARY KEY,
    username TEXT REFERENCES users NOT NULL,
    symbol TEXT,
    name TEXT,
    type TEXT,
    quantity INTEGER,
    price NUMERIC,
    date_of_trade TIMESTAMPTZ
)
SELECT symbol, SUM(value) AS profit
FROM (
    SELECT symbol, type, CASE
        WHEN type='Buy' THEN -SUM(price * quantity)
        WHEN type='Sell' THEN SUM(price * quantity)
    END AS value
    FROM trades
    GROUP BY username, symbol, type
) sub
GROUP BY username, symbol;


INSERT INTO trades VALUES(1, 'justinwustin200', 'AAPL', 'Apple Inc.', 'Buy', 2, 100.00, '2022-06-08 09:30:30 America/New_York');
quantity(buys(asset)):
SELECT SUM(quantity) FROM trades WHERE type = "Buy" GROUP BY username, symbol

quantity(sells(asset)):
SELECT SUM(quantity) FROM trades WHERE type = "Sell" GROUP BY username, symbol

