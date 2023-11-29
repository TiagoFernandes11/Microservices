package com.udemy.Cards.service.impl;

import com.udemy.Cards.entity.Cards;
import com.udemy.Cards.entity.Customer;
import com.udemy.Cards.repository.CardsRepository;
import com.udemy.Cards.service.ICardsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Random;

public class CardsServiceImpl implements ICardsService {

    @Override
    public Cards createCard(Customer customer) {
        Cards newCard = new Cards();
        newCard.setCustomerId(customer.getCustomerId());
        newCard.setCardNumber(new Random().nextLong(90000000000L) + 10000000000L + "");
        newCard.setCardType("Credit");
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(5000);
        newCard.setTotalLimit(5000);
        newCard.setCreatedDt(LocalDateTime.now());
        return newCard;
    }


}
