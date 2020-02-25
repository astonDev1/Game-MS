package com.astontech.game.services.Impl;

import com.astontech.game.domain.GameData;
import com.astontech.game.repositories.GameDataRepository;
import com.astontech.game.services.GameDataService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameDataServiceImpl implements GameDataService {

    GameDataRepository gameDataRepository;
    public GameDataServiceImpl(GameDataRepository gameDataRepository){
        this.gameDataRepository = gameDataRepository;
    }

    @Override
    public List<GameData> findAll() {
        return gameDataRepository.findAll();
    }

    @Override
    public GameData findByGameDataId(String id) {
        return gameDataRepository.findById(id).orElse(null);
    }

    @Override
    public GameData findByGameDate(LocalDateTime localDateTime) {
        return gameDataRepository.findByGameDate(localDateTime);
    }

    @Override
    public GameData saveGameData(GameData gameData) {
        return gameDataRepository.save(gameData);
    }

    @Override
    public List<GameData> saveAll(List<GameData> gameData) {
        return gameDataRepository.saveAll(gameData);
    }

    @Override
    public void deleteById(String id) {
        gameDataRepository.deleteById(id);
    }
}
