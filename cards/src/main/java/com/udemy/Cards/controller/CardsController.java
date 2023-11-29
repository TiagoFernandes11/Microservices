package com.udemy.Cards.controller;

import com.udemy.Cards.entity.Cards;
import com.udemy.Cards.entity.Customer;
import com.udemy.Cards.repository.CardsRepository;
import com.udemy.Cards.service.impl.CardsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsServiceImpl service;

    @GetMapping
    public ResponseEntity<Cards> getCardsByCustomerId(@RequestBody Customer customer){
        Optional<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        if(cards.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(cards.get());
        }
        else {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping
    public void createNewCard(@RequestBody Customer customer){
        cardsRepository.save(service.createCard(customer));
    }
}
