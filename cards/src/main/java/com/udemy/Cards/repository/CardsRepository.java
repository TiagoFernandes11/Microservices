package com.udemy.Cards.repository;

import com.udemy.Cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Integer> {
}
