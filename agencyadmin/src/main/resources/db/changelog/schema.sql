
CREATE TABLE  IF NOT EXISTS buses (
    busid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    busplatenumber VARCHAR(50) NOT NULL UNIQUE,
    buscapacity INT NOT NULL,
    bustype VARCHAR(50) NOT NULL,
    agencyid VARCHAR(100) NOT NULL
);
