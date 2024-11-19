package org.example.carrentalapi.service;

import org.example.carrentalapi.exception.ResourceNotFoundException;
import org.example.carrentalapi.model.Car;
import org.example.carrentalapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    private static final List<String> VALID_LOCATIONS = Arrays.asList("Plovdiv", "Sofia", "Varna", "Burgas");

    public List<Car> getAllCars(String location) {
        if (!VALID_LOCATIONS.contains(location)) {
            throw new IllegalArgumentException("Invalid location: " + location);
        }
        return carRepository.findByLocationAndIsValidTrue(location);
    }

    public Car getCarById(Long id) {
        return carRepository.findByIdAndIsValidTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
    }

    public Car createCar(Car car) {
        if (!VALID_LOCATIONS.contains(car.getLocation())) {
            throw new IllegalArgumentException("Invalid location: " + car.getLocation());
        }
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car carDetails) {
        if (!VALID_LOCATIONS.contains(carDetails.getLocation())) {
            throw new IllegalArgumentException("Invalid location: " + carDetails.getLocation());
        }
        return carRepository.findByIdAndIsValidTrue(id)
                .map(car -> {
                    car.setMake(carDetails.getMake());
                    car.setModel(carDetails.getModel());
                    car.setLocation(carDetails.getLocation());
                    car.setPricePerDay(carDetails.getPricePerDay());
                    return carRepository.save(car);
                }).orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findByIdAndIsValidTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
        car.setValid(false);
        carRepository.save(car);
    }
}