CREATE TABLE IF NOT EXISTS refresh_tokens (
                                refresh_token_id BIGSERIAL PRIMARY KEY,
                                token VARCHAR(255) NOT NULL UNIQUE,
                                expiry_date TIMESTAMP NOT NULL,
                                user_id BIGINT NOT NULL,
                                CONSTRAINT fk_refresh_tokens_users
                                    FOREIGN KEY (user_id)
                                    REFERENCES users(id)
                                        ON DELETE CASCADE
);