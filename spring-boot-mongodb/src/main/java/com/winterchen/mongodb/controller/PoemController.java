package com.winterchen.mongodb.controller;

import com.winterchen.mongodb.model.Poem;
import com.winterchen.mongodb.service.PoemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/poem")
public class PoemController {

    @Autowired
    private PoemService poemService;


    @PostMapping("")
    public Map<String, Object> insertOne(
            @RequestBody
            Poem poem
    ) {
        try {
            return ok(poemService.insertOne(poem));
        } catch (Throwable throwable) {
            log.error(throwable.getMessage());
        }
        return fail("rollback");
    }

    @PostMapping("/batch")
    public Map<String, Object> inertMany(
            @RequestBody
            List<Poem> poemList
    ) {
        return ok(poemService.inertMany(poemList));
    }

    @DeleteMapping("")
    public Map<String, Object> deleteByAuthor(
            @RequestParam(name = "author")
            String author
    ) {
        poemService.deleteByAuthor(author);
        return ok();
    }

    @PutMapping("")
    public Map<String, Object> updateFirst(
            @RequestBody
            Poem poem
    ) {
        poemService.updateFirst(poem);
        return ok();
    }

    @PutMapping("/multi")
    public Map<String,Object> updateMulti(
            @RequestBody
            Poem poem
    ){
        poemService.updateMulti(poem);
        return ok();
    }

    @PutMapping("/upsert")
    public Map<String, Object> upsert(
            @RequestBody
            Poem poem
    ) {
        poemService.upsert(poem);
        return ok();
    }

    @GetMapping("")
    public Map<String, Object> selectManyByAuthor(
            @RequestParam(name = "author")
                    String author
    ) {
        return ok(poemService.selectManyByAuthor(author));
    }

    private Map<String, Object> ok() {
        Map<String, Object> result = new HashMap<>();
        result.put("result","ok");
        return result;
    }

    private Map<String, Object> ok(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("result","ok");
        result.put("data", data);
        return result;
    }

    private Map<String, Object> fail(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("result","fail");
        result.put("message", message);
        return result;
    }

}
