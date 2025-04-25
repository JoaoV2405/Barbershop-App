ALTER TABLE clients
    ADD password VARCHAR(24)DEFAULT 'password';

ALTER TABLE clients
    ALTER COLUMN password SET NOT NULL;