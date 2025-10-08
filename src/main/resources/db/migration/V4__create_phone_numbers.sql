CREATE TABLE IF NOT EXISTS phone_numbers(
    id BIGSERIAL PRIMARY KEY,
    contact_id BIGINT NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,

    CONSTRAINT fk_phone_number_contacts
    FOREIGN KEY (contact_id)
    REFERENCES contacts (id)

)
