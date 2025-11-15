CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    address VARCHAR(255),
    birthday DATE
);

CREATE TABLE `groups` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at DATE,
    updated_at DATE
);

CREATE TABLE `user_group_mappings` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    group_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (group_id) REFERENCES `groups` (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

ALTER TABLE users ADD COLUMN username VARCHAR(255);

ALTER TABLE users ADD COLUMN `role` VARCHAR(255);

ALTER TABLE users ADD COLUMN `password` VARCHAR(255);

ALTER TABLE users ADD COLUMN deleted BOOLEAN DEFAULT FALSE;