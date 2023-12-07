package com.udemy.accounts.service.client;

import com.udemy.accounts.dto.CardsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardsDTO> fetchCardDetails(String mobileNumber) {
        return null;
    }
}
