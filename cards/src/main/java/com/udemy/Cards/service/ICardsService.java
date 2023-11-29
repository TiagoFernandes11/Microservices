package com.udemy.Cards.service;

import com.udemy.Cards.entity.Cards;
import com.udemy.Cards.entity.Customer;

public interface ICardsService {
    Cards createCard(Customer customer);
}
