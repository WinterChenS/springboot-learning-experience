package com.winterchen.springbootelasticsearch.controller;

import com.winterchen.springbootelasticsearch.model.Poem;
import com.winterchen.springbootelasticsearch.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/poem")
public class PoemController {

    @Autowired
    private PoemService poemService;


    @PostMapping("/index")
    public Map<String, Object> createIndex() {
        poemService.createIndex();
        return ok();
    }

    @DeleteMapping("/index")
    public Map<String, Object> deleteIndex(
            @RequestParam("index")
            String index
    ) {
        poemService.deleteIndex(index);
        return ok();
    }

    @PostMapping("")
    public Map<String, Object> save(
            @RequestBody
            Poem poem
    ) {
        poemService.save(poem);
        return ok();
    }

    @PostMapping("/batch")
    public Map<String, Object> saveAll(
            @RequestBody
            List<Poem> list
    ) {
        poemService.saveAll(list);
        return ok();
    }

    @GetMapping("/all")
    public Map<String, Object> findAll() {
        return ok(poemService.findAll());
    }

    @GetMapping("/by-paragraphs")
    public Map<String, Object> findByParagraphs(
            @RequestParam("content")
            String content,
            @RequestParam(name = "pageNum", defaultValue = "1", required = false)
            int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false)
            int pageSize
    ) {
        return ok(poemService.findByParagraphs(content, pageNum, pageSize));
    }

    @GetMapping("/by-author")
    public Map<String, Object> findByAuthor(
            @RequestParam("author")
            String author,
            @RequestParam(name = "pageNum", defaultValue = "1", required = false)
                    int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false)
                    int pageSize
    ) {
        return ok(poemService.findByAuthor(author, pageNum, pageSize));
    }

    @GetMapping("/first-by-rhythmic")
    public Map<String, Object> findFirstByRhythmic(
            @RequestParam("rhythmic")
            String rhythmic,
            @RequestParam(name = "pageNum", defaultValue = "1", required = false)
                    int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false)
                    int pageSize
    ) {
        return ok(poemService.findFirstByRhythmic(rhythmic, pageNum, pageSize));
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
