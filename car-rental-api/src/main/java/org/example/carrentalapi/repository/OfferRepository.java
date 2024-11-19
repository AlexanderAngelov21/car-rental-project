package org.example.carrentalapi.repository;

import org.example.carrentalapi.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByCustomerNameAndIsValidTrue(String customerName);
    Optional<Offer> findByIdAndIsValidTrue(Long id);
}