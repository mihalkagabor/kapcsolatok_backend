CREATE TABLE IF NOT EXISTS adress(
     id BIGSERIAL PRIMARY KEY,
    contact_id BIGINT NOT NULL ,
    zip_code VARCHAR(255) NOT NULL ,
    city VARCHAR(255) NOT NULL ,
    street VARCHAR(255) NOT NULL ,
    house_number VARCHAR(255) NOT NULL,


    CONSTRAINT fk_adress_contact
    FOREIGN KEY (contact_id)
    REFERENCES contacts(id)


)
