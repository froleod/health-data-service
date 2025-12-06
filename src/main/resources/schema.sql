CREATE TABLE IF NOT EXISTS health_data (
    id BIGSERIAL PRIMARY KEY,
    user_id UUID,
    device_id VARCHAR(255),
    heart_rate INT,
    steps INT,
    calories DOUBLE PRECISION,
    systolic_pressure INT,
    diastolic_pressure INT,
    glucose DOUBLE PRECISION,
    spo2 INT,
    source VARCHAR(50),
    timestamp TIMESTAMPTZ
);
