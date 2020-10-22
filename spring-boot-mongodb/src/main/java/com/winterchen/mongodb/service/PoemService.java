package com.winterchen.mongodb.service;

import com.winterchen.mongodb.model.Poem;

import java.util.List;

public interface PoemService {

    Poem insertOne(Poem poem) throws Throwable;

    List<Poem> inertMany(List<Poem> poemList);


    void deleteByAuthor(String author);

    void updateFirst(Poem poem);

    void updateMulti(Poem poem);

    void upsert(Poem poem);

    List<Poem> selectManyByAuthor(String author);



}
