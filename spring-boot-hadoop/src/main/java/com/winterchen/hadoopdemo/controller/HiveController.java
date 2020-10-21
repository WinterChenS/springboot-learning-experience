package com.winterchen.hadoopdemo.controller;

import com.winterchen.hadoopdemo.service.HiveService;
import com.winterchen.hadoopdemo.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "hive")
@RequestMapping("/api/v1/hive")
@RestController
public class HiveController {

    @Autowired
    private HiveService hiveService;

    @ApiOperation("select by hql")
    @GetMapping("/hql")
    public APIResponse selectByHql(
            @ApiParam(name = "hql", required = true)
            @RequestParam(name = "hql", required = true)
            String hql
    ) {
        return APIResponse.success(hiveService.select(hql));
    }

    @ApiOperation("show all tables")
    @GetMapping("/tables/all")
    public APIResponse<List<String>> listAllTables() {
        return APIResponse.success(hiveService.listAllTables());
    }

    @ApiOperation("describe table info")
    @GetMapping("/table/info")
    public APIResponse<List<String>> describeTable(
            @ApiParam(name = "tableName", required = true)
            @RequestParam(name = "tableName", required = true)
            String tableName
    ){
        return APIResponse.success(hiveService.describeTable(tableName));
    }

    @ApiOperation("select data from table")
    @GetMapping("/table/data")
    public APIResponse<List<String>> selectFromTable(
            @ApiParam(name = "tableName", required = true)
            @RequestParam(name = "tableName", required = true)
                    String tableName
    ) {
        return APIResponse.success(hiveService.selectFromTable(tableName));
    }

}
