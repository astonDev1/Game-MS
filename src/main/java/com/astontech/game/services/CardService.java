package com.astontech.game.services;

import com.astontech.game.domain.Card;
import com.astontech.game.domain.Tags;

import java.util.List;

public interface CardService {

    List<Card> findAll();
    Card findById(String id);
    Card findByDifficulty(String difficulty);
    List<Card> findAllByTagsIsContaining(List<String> params);
    Card saveCard(Card card);
    List<Card> saveAll(List<Card> cards);
    void deleteById(String id);
}
