package com.udemy.Cards.service;

import com.udemy.Cards.dto.CardsDto;

public interface ICardsService {
    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);

}
