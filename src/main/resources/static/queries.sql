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
    trade_id SERIAL,
    symbol TEXT,
    name TEXT,
    type TEXT,
    quantity INTEGER,
    price NUMERIC,
    date_of_trade TIMESTAMP
)

quantity(buys(asset)):
SELECT SUM(quantity) FROM trades WHERE type = "Buy" GROUP BY username, symbol

quantity(sells(asset)):
SELECT SUM(quantity) FROM trades WHERE type = "Sell" GROUP BY username, symbol

quantity(asset):
quantity(buys(asset)) - quantity(sells(asset))