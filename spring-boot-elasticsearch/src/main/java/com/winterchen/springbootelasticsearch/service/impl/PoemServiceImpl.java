package com.winterchen.springbootelasticsearch.service.impl;

import com.winterchen.springbootelasticsearch.dao.ElasticRepository;
import com.winterchen.springbootelasticsearch.model.Poem;
import com.winterchen.springbootelasticsearch.service.PoemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author winterchen 2020/10/22
 */
@Slf4j
@Service
public class PoemServiceImpl implements PoemService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private ElasticRepository elasticRepository;


    @Override
    public void createIndex() {
        elasticsearchTemplate.createIndex(Poem.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(Poem.class);
    }

    @Override
    public void save(Poem poem) {
        elasticRepository.save(poem);
    }

    @Override
    public void saveAll(List<Poem> list) {
        elasticRepository.saveAll(list);
    }

    @Override
    public Iterator<Poem> findAll() {
        return elasticRepository.findAll().iterator();
    }

    @Override
    public Page<Poem> findByParagraphs(String content, int pageNum, int pageSize) {
        int i = pageNum - 1;
        Pageable pageable = PageRequest.of(i, pageSize);
        return elasticRepository.findByParagraphs(content, pageable);
    }

    @Override
    public Page<Poem> findByAuthor(String author, int pageNum, int pageSize) {
        int i = (pageNum - 1) * pageSize;
        Pageable pageable = PageRequest.of(i, pageSize);
        return elasticRepository.findByAuthor(author, pageable);
    }

    @Override
    public Page<Poem> findFirstByRhythmic(String rhythmic, int pageNum, int pageSize) {
        int i = (pageNum - 1) * pageSize;
        Pageable pageable = PageRequest.of(i, pageSize);
        return elasticRepository.findFirstByRhythmic(rhythmic, pageable);
    }

    @Override
    public Page<Poem> query(String key, int pageNum, int pageSize) {
        int i = (pageNum - 1) * pageSize;
        Pageable pageable = PageRequest.of(i, pageSize);
        return elasticRepository.findByParagraphs(key, pageable);
    }
}
