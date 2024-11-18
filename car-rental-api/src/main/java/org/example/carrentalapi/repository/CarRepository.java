package org.example.carrentalapi.repository;

import org.example.carrentalapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByLocation(String location);
}