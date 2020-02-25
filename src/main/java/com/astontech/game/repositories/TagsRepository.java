package com.astontech.game.repositories;

import com.astontech.game.domain.Tags;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagsRepository extends MongoRepository<Tags, String> {

    Tags findTopByTagName(String tagName);
}
