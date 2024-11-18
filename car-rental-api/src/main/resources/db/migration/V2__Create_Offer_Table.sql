CREATE TABLE offer (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       customer_name VARCHAR(255) NOT NULL,
                       customer_address VARCHAR(255) NOT NULL,
                       customer_phone VARCHAR(255) NOT NULL,
                       customer_age INT NOT NULL,
                       has_accidents BOOLEAN NOT NULL,
                       car_id BIGINT NOT NULL,
                       start_date DATE NOT NULL,
                       end_date DATE NOT NULL,
                       total_price DOUBLE NOT NULL,
                       CONSTRAINT fk_car FOREIGN KEY (car_id) REFERENCES car(id)
);