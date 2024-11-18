package org.example.carrentalapi.service;

import org.example.carrentalapi.model.Offer;
import org.example.carrentalapi.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllOffers(String customerName) {
        return offerRepository.findByCustomerName(customerName);
    }

    public Optional<Offer> getOfferById(Long id) {
        return offerRepository.findById(id);
    }

    public Offer createOffer(Offer offer) {
        double totalPrice = calculateTotalPrice(offer.getStartDate(), offer.getEndDate(), offer.getTotalPrice(), offer.isHasAccidents());
        offer.setTotalPrice(totalPrice);
        return offerRepository.save(offer);
    }

    public void deleteOffer(Long id) {
        offerRepository.findById(id).ifPresent(offerRepository::delete);
    }

    private double calculateTotalPrice(LocalDate startDate, LocalDate endDate, double dailyPrice, boolean hasAccidents) {
        double totalPrice = 0.0;
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                totalPrice += dailyPrice * 1.10;
            } else {
                totalPrice += dailyPrice;
            }
            date = date.plusDays(1);
        }

        if (hasAccidents) {
            totalPrice += 200;
        }

        return totalPrice;
    }
}