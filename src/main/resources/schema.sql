CREATE TABLE IF NOT EXISTS health_data (
    id IDENTITY PRIMARY KEY,
    device_id VARCHAR(255),
    heart_rate INT,
    steps INT,
    calories DOUBLE,
    timestamp TIMESTAMP
);
