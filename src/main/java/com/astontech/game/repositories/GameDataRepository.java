package com.astontech.game.repositories;

import com.astontech.game.domain.GameData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface GameDataRepository extends MongoRepository<GameData, String> {

    GameData findByGameDate(LocalDateTime localDateTime);
}
