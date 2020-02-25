package com.astontech.game.services;

import com.astontech.game.domain.GameData;

import java.time.LocalDateTime;
import java.util.List;

public interface GameDataService {

    List<GameData> findAll();
    GameData findByGameDataId(String id);
    GameData findByGameDate(LocalDateTime localDateTime);
    GameData saveGameData(GameData gameData);
    List<GameData> saveAll(List<GameData> gameData);
    void deleteById(String id);
}
