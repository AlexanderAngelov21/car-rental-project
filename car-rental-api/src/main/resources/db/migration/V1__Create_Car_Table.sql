CREATE TABLE car (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     make VARCHAR(255) NOT NULL,
                     model VARCHAR(255) NOT NULL,
                     location VARCHAR(255) NOT NULL,
                     price_per_day DOUBLE NOT NULL
);