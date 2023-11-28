package com.udemy.Cards.controller;

import com.udemy.Cards.entity.Cards;
import com.udemy.Cards.entity.Customer;
import com.udemy.Cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping
    public ResponseEntity<Cards> getCardsByCustomerId(@RequestBody Customer customer){
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        if(cards != null){
            return new ResponseEntity<>(
                    HttpStatus.CREATED
            );
        }
        else {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
