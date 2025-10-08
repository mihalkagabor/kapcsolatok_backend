CREATE TABLE IF NOT EXISTS contacts(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL ,
    mother_name VARCHAR(255) NOT NULL ,
    taj_number VARCHAR(255) NOT NULL,
    tax_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL ,

    CONSTRAINT fk_contacts_user
    FOREIGN KEY (user_id)
    REFERENCES users (id)
)