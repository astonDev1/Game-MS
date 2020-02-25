package com.astontech.game.controllers.crud;

import com.astontech.game.domain.Card;
import com.astontech.game.services.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private static final Logger log = LoggerFactory.getLogger(CardController.class);

    CardService cardService;
    Environment environment;
    public CardController(CardService cardService, Environment environment){
        this.cardService = cardService;
        this.environment = environment;
    }

    @GetMapping("/status")
    public String status(){
        log.info("Status EndPoint Hit");
        return "Working on port " + " " + environment.getProperty("local.server.port");
    }

    @GetMapping("/")
    public ResponseEntity<List<Card>> getAllCards(){
        List<Card> foundCards = cardService.findAll();
        if (foundCards == null){
            log.info("No Cards In System Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        log.info("Cards found, total Count : " + (long) foundCards.size());
        return ResponseEntity.status(HttpStatus.OK).body(foundCards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable String id){
        Card foundCard = cardService.findById(id);
        if (foundCard.getId().equals("") || foundCard.getId() == null){
            log.info("Bad Request, Card With Id Of " + id + " " + "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundCard);
        }
        log.info("Card With Id Of " + " " + id + " " + "Found");
        return ResponseEntity.status(HttpStatus.OK).body(foundCard);
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<Card> getCardByDifficulty(@PathVariable String difficulty){
        Card foundCard = cardService.findByDifficulty(difficulty);
        if (foundCard.getId().equals("") || foundCard.getId() == null){
            log.info("Bad Request, Card With Difficulty Of " + difficulty + " " + "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundCard);
        }
        log.info("Card With Difficulty Of " + " " + difficulty + " " + "Found");
        return ResponseEntity.status(HttpStatus.OK).body(foundCard);
    }

    @GetMapping("/tags/{param}")
    public ResponseEntity<List<Card>> getCardByTags(@PathVariable List<String> param){
        List<Card> foundCards = cardService.findAllByTagsIsContaining(param);
        if (foundCards == null){
            log.info("No Cards In System With Selected Tag Parameters");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        log.info("Cards Found, Total Count Containing Parameters : " + " " + (long) foundCards.size());
        return ResponseEntity.status(HttpStatus.OK).body(foundCards);
    }

    @PostMapping("/")
    public ResponseEntity<Card> saveCard(@RequestBody Card card){
        Card savedCard = cardService.saveCard(card);
        if (savedCard.getId().equals("") || savedCard.getId() == null){
            log.info("Bad Save, Card Not Saved");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savedCard);
        }
        log.info("Card Saved And Inserted Successfully! Card Info : " + savedCard.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCard);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Card>> saveAll(@RequestBody List<Card> cards){
        List<Card> savedCards = cardService.saveAll(cards);
        if (savedCards == null){
            log.info("No Cards Inserted, Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        log.info("All Cards Saved : " + savedCards.stream().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCards);
    }

    @PutMapping("/")
    public ResponseEntity<Card> updateCard(@RequestBody Card card){
        Card updatedCard = cardService.saveCard(card);
        if (updatedCard.getQuestion().equals("") || updatedCard.getQuestion() == null){
            log.info("Bad Update, Please Insert Question Parameters Into Card!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCard);
        }
        log.info("Card Updated Successfully. Card Info : " + " " + card.toString());
        return ResponseEntity.status(HttpStatus.OK).body(updatedCard);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable String id){
        Card idToDelete = cardService.findById(id);
        cardService.deleteById(idToDelete.getId());
        if (idToDelete.getId() != null){
            log.info("Delete Of Id : " + id + " " + " Failed!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(idToDelete);
        }
        log.info("Card Deleted Successfully!");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


}
