package com.astontech.game.services.Impl;

import com.astontech.game.domain.Tags;
import com.astontech.game.repositories.TagsRepository;
import com.astontech.game.services.TagsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    TagsRepository tagsRepository;
    public TagsServiceImpl(TagsRepository tagsRepository){
        this.tagsRepository = tagsRepository;
    }

    @Override
    public List<Tags> findAllTags() {
        return tagsRepository.findAll();
    }

    @Override
    public Tags findTagById(String id) {
        return tagsRepository.findById(id).orElse(null);
    }

    @Override
    public Tags findTopByTagName(String tagName) {
        return tagsRepository.findTopByTagName(tagName);
    }

    @Override
    public Tags saveTags(Tags tags) {
        return tagsRepository.save(tags);
    }

    @Override
    public List<Tags> saveAll(List<Tags> tags) {
        return tagsRepository.saveAll(tags);
    }

    @Override
    public void deleteTagById(String id) {
        tagsRepository.deleteById(id);
    }
}
