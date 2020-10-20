package com.winterchen.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "winterchen")
public class Poem {

    @Id
    private String id;

    @Field("author")
    private String author;

    @Field("paragraphs")
    private String paragraphs;

    @Field("rhythmic")
    private String rhythmic;

}
