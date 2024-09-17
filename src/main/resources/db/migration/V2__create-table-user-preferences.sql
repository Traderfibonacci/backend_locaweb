CREATE TABLE tbl_user_preferences (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theme VARCHAR(255),
    color VARCHAR(255),
    category VARCHAR(255),
    user_account_id BIGINT,
    CONSTRAINT fk_user_account FOREIGN KEY (user_account_id)
        REFERENCES tbl_user_account(id)
        ON DELETE CASCADE
);