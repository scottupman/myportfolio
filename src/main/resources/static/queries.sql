CREATE TABLE positions
(
    id SERIAL PRIMARY KEY,
    portfolio_id INTEGER REFERENCES portfolio NOT NULL,
    name TEXT,
    symbol TEXT,
    current_price NUMERIC,
    price_bought NUMERIC,
    quantity INTEGER,
    profit_loss NUMERIC,
    is_crypto BOOLEAN
)