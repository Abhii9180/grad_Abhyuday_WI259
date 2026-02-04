-- TABLE CREATION SCRIPT
CREATE TABLE users (
    user_id INTEGER PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    role VARCHAR(10)
);

CREATE TABLE site_owners (
    owner_id INTEGER PRIMARY KEY,
    owner_name VARCHAR(100),
    contact_number VARCHAR(15),
    email VARCHAR(100),
    FOREIGN KEY (owner_id) REFERENCES users(user_id)
);

CREATE TABLE sites (
    site_id INTEGER PRIMARY KEY,
    length INTEGER,
    width INTEGER,
    site_type VARCHAR(15),
    maintenance_status VARCHAR(15),
    owner_id INTEGER,
    FOREIGN KEY (owner_id) REFERENCES site_owners(owner_id)
);

CREATE TABLE maintenance (
    maintenance_id INTEGER PRIMARY KEY,
    site_id INTEGER,
    amount NUMERIC(10,2),
    due_date DATE,
    payment_date DATE,
    status VARCHAR(15),
    FOREIGN KEY (site_id) REFERENCES sites(site_id)
);

CREATE TABLE update_requests (
    request_id INTEGER PRIMARY KEY,
    owner_id INTEGER,
    site_id INTEGER,
    requested_field VARCHAR(50),
    old_value VARCHAR(100),
    new_value VARCHAR(100),
    status VARCHAR(15),
    FOREIGN KEY (owner_id) REFERENCES site_owners(owner_id),
    FOREIGN KEY (site_id) REFERENCES sites(site_id)
);
