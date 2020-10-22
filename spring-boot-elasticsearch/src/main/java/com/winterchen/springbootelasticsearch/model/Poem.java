package com.winterchen.springbootelasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author winterchen 2020/10/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(indexName = "poem", shards = 1, replicas = 0)
public class Poem {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String author;

    @Field(type = FieldType.Keyword, analyzer = "ik_max_word")
    private List<String> paragraphs;

    @Field(type = FieldType.Keyword)
    private String rhythmic;

}
