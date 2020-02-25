package com.astontech.game.services.Impl;

import com.astontech.game.domain.Card;
import com.astontech.game.domain.Tags;
import com.astontech.game.repositories.CardRepository;
import com.astontech.game.services.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    CardRepository cardRepository;
    public CardServiceImpl(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card findById(String id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public Card findByDifficulty(String difficulty) {
        return cardRepository.findByDifficulty(difficulty);
    }

    @Override
    public List<Card> findAllByTagsIsContaining(List<String> params) {
        return cardRepository.findAllByTagsIsContaining(params);
    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> saveAll(List<Card> cards) {
        return cardRepository.saveAll(cards);
    }

    @Override
    public void deleteById(String id) {
        cardRepository.deleteById(id);
    }
}
