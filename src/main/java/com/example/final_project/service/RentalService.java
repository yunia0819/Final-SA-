package com.example.final_project.service;


import com.example.final_project.entity.Rental;
import com.example.final_project.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public Rental createRental(String furnitureName, int quantity, String date, Double totalPrice) {
        Rental rental = new Rental();
        rental.setUserId(1); // 預設 userId 為 1，可根據需求修改
        rental.setFurnitureName(furnitureName);
        rental.setQuantity(quantity);
        rental.setDate(date);
        rental.setTotalPrice(totalPrice);
        rental.setPayed(true);

        return rentalRepository.save(rental);
    }
}
