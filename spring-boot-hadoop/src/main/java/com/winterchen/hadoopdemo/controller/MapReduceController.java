package com.winterchen.hadoopdemo.controller;

import com.winterchen.hadoopdemo.service.MapReduceService;
import com.winterchen.hadoopdemo.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "map reduce api")
@RestController
@RequestMapping("/api/v1/map-reduce")
public class MapReduceController {


    @Autowired
    private MapReduceService mapReduceService;

    @ApiOperation("count word")
    @PostMapping("/word/count")
    public APIResponse wordCount(
            @ApiParam(name = "jobName", required = true)
            @RequestParam(name = "jobName", required = true)
            String jobName,
            @ApiParam(name = "inputPath", required = true)
            @RequestParam(name = "inputPath", required = true)
            String inputPath,
            @ApiParam(name = "outputPath", required = true)
            @RequestParam(name = "outputPath", required = true)
            String outputPath
    ){
        try {
            mapReduceService.wordCount(jobName, inputPath, outputPath);
            return APIResponse.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return APIResponse.fail(e.getMessage());
        }
    }
}
