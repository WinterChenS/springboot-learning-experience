package com.winterchen.mongodb.service.impl;

import com.winterchen.mongodb.model.Poem;
import com.winterchen.mongodb.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PoemServiceImpl implements PoemService {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Poem insertOne(Poem poem) {
        return mongoTemplate.save(poem);
    }

    @Override
    public List<Poem> inertMany(List<Poem> poemList) {
        return (List<Poem>) mongoTemplate.insert(poemList, Poem.class);
    }



    @Override
    public void deleteByAuthor(String author) {
        if (StringUtils.isEmpty(author)) {
            return;
        }
        Query query = Query.query(Criteria.where("author").is(author));
        mongoTemplate.findAllAndRemove(query, Poem.class);
    }

    @Override
    public void updateFirst(Poem poem) {
        Query query = Query.query(Criteria.where("author").is(poem.getAuthor()));
        Update update = Update.update("paragraphs",poem.getParagraphs()).set("rhythmic",poem.getRhythmic());
        mongoTemplate.updateFirst(query,update,Poem.class);
    }

    @Override
    public void updateMulti(Poem poem) {
        Query query = Query.query(Criteria.where("author").is(poem.getAuthor()));
        Update update = Update.update("author",poem.getAuthor())
                .set("paragraphs",poem.getParagraphs()).set("rhythmic",poem.getRhythmic());
        mongoTemplate.updateMulti(query, update, Poem.class);
    }

    @Override
    public void upsert(Poem poem) {
        Query query = Query.query(Criteria.where("author").is(poem.getAuthor()));
        Update update = Update.update("paragraphs",poem.getParagraphs()).set("rhythmic",poem.getRhythmic());
        mongoTemplate.updateMulti(query,update,Poem.class);
    }

    @Override
    public List<Poem> selectManyByAuthor(String author) {
        Query query = Query.query(Criteria.where("author").is(author));
        return mongoTemplate.find(query, Poem.class);
    }

}
