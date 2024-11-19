package org.example.carrentalapi.repository;

import org.example.carrentalapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByLocationAndIsValidTrue(String location);
    Optional<Car> findByIdAndIsValidTrue(Long id);
}