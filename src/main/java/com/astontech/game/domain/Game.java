package com.astontech.game.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private String id;
    private Integer playerCount;
    private Integer cardCount;
    private Integer timer;
    private List<String> leaderBoard;
    private List<Integer> tempUserScores;
    private List<Card> cards;
    private GameData gameData;

}
