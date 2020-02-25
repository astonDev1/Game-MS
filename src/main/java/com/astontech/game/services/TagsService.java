package com.astontech.game.services;

import com.astontech.game.domain.Tags;

import java.util.List;

public interface TagsService {

    List<Tags> findAllTags();
    Tags findTagById(String id);
    Tags findTopByTagName(String tagName);
    Tags saveTags(Tags tags);
    List<Tags> saveAll(List<Tags> tags);
    void deleteTagById(String id);

}
