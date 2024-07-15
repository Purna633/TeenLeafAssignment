CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_type INT NOT NULL DEFAULT 1
);
select * from users;
describe users;

