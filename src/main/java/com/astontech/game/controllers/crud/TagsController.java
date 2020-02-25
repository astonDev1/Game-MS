package com.astontech.game.controllers.crud;

import com.astontech.game.domain.Tags;
import com.astontech.game.services.TagsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    private static final Logger log = LoggerFactory.getLogger(TagsController.class);

    TagsService tagsService;
    Environment environment;
    public TagsController(TagsService tagsService, Environment environment){
        this.tagsService = tagsService;
        this.environment = environment;
    }

    @GetMapping("/status")
    public String getStatus(){
        log.info("Status EndPoint Hit");
        return "Working on port " + " " + environment.getProperty("local.server.port");
    }

    @GetMapping("/")
    public ResponseEntity<List<Tags>> getAllTags(){
        List<Tags> foundTags = tagsService.findAllTags();
        if (foundTags == null){
            log.info("No Tags In System or bad request, please enter tags");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        log.info("Tags Found, Amount :  " + " " + foundTags.size());
        return ResponseEntity.status(HttpStatus.OK).body(foundTags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tags> getTagById(@PathVariable String id){
        Tags foundTag = tagsService.findTagById(id);
        if (foundTag.getTagName().equals("") || foundTag.getTagName() == null){
            log.info("Empty Tag, Please Retry Tag ID Param");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundTag);
        }
        log.info("Tag Found: " + foundTag.toString());
        return ResponseEntity.status(HttpStatus.OK).body(foundTag);
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<Tags> getTagByName(@PathVariable String name){
        Tags foundTag = tagsService.findTopByTagName(name);
        if (foundTag.getId().equals("") || foundTag.getId() == null){
            log.info("Empty Tag, Please Retry Tag Name Param");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundTag);
        }
        log.info("Tag Found: " + foundTag.toString());
        return ResponseEntity.status(HttpStatus.OK).body(foundTag);
    }

    @PostMapping("/")
    public ResponseEntity<Tags> saveTag(@RequestBody Tags tags){
        Tags savedTag = tagsService.saveTags(tags);
        if (savedTag.getId() == null || savedTag.getTagName().equals("")){
            log.info("Bad Creation, Please Check Insert Parameters");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savedTag);
        }
        log.info("Tag Successfully saved. Tag Info : " + " " + savedTag.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
    }

    @PutMapping("/")
    public ResponseEntity<Tags> updateTags(@RequestBody Tags tags){
        Tags updatedTag = tagsService.saveTags(tags);
        if (updatedTag.getTagName().equals("") || updatedTag.getTagName() == null){
            log.info("Bad update, Please Check Update Parameters");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedTag);
        }
        log.info("Tag Successfully Updated. Tag Info : " + " " + updatedTag.toString());
        return ResponseEntity.status(HttpStatus.OK).body(updatedTag);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Tags>> saveAll(@RequestBody List<Tags> tags){
        List<Tags> savedTags = tagsService.saveAll(tags);
        if (savedTags == null){
            log.info("No Tags In System or bad request, please save tags in correct format");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        log.info("Tags Saved Successfully : " + " " + savedTags.stream().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTags);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTag(@PathVariable String id){
        Tags tagToDelete = tagsService.findTagById(id);
        tagsService.deleteTagById(tagToDelete.getId());
        if (tagToDelete.getTagName() != null){
            log.info("Delete Of Id : " + id + " " + " Failed!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tagToDelete);
        }
        log.info("Tag Deleted Successfully!");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


}
