CREATE TABLE artifacts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    element VARCHAR(50) NOT NULL,
    power_level INT NOT NULL
);

CREATE TABLE mages (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    rank VARCHAR(50) NOT NULL,
    mana_pool INT NOT NULL
);