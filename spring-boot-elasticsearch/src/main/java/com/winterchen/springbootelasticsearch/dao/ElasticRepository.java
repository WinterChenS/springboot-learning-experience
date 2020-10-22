package com.winterchen.springbootelasticsearch.dao;

import com.winterchen.springbootelasticsearch.model.Poem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author winterchen 2020/10/22
 */
public interface ElasticRepository extends ElasticsearchRepository<Poem, Long> {

    Page<Poem> findByParagraphs(String Paragraphs, Pageable pageable);

    Page<Poem> findByAuthor(String author, Pageable pageable);

    Page<Poem> findFirstByRhythmic(String rhythmic, Pageable pageable);

}
