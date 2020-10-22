package com.winterchen.springbootelasticsearch.service;

import com.winterchen.springbootelasticsearch.model.Poem;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * @author winterchen 2020/10/22
 */
public interface PoemService {


    void createIndex();

    void deleteIndex(String index);

    void save(Poem docBean);

    void saveAll(List<Poem> list);

    Iterator<Poem> findAll();

    Page<Poem> findByParagraphs(String content, int pageNum, int pageSize);

    Page<Poem> findByAuthor(String author, int pageNum, int pageSize);

    Page<Poem> findFirstByRhythmic(String rhythmic, int pageNum, int pageSize);

    Page<Poem> query(String key, int pageNum, int pageSize);
}
