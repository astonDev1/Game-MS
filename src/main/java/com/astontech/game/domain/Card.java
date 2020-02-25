package com.astontech.game.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    private String id;

    private String question;
    private List<String> correct;
    private List<String> incorrect;

    @Indexed
    private List<Tags> tags;
    private String explanation;

    @Indexed
    private String difficulty;

}
