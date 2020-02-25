package com.astontech.game.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class GameData {

    @Id
    private String id;

    @Indexed
    private LocalDateTime gameDate;

    private Integer playerCount;
    private Integer cardCount;
    private String gameHost;
    private List<String> playerUserNames;
    private List<Tags> tagsUsed;
    private Integer answerPercentage;
    private Integer avgUserScore;
    private Integer avgRoundTime;
    private Integer totalTime;

}
