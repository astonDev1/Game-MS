package com.astontech.game.repositories;

import com.astontech.game.domain.Card;
import com.astontech.game.domain.Tags;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {

    Card findByDifficulty(String difficulty);
    List<Card> findAllByTagsIsContaining(List<String> params);
}
