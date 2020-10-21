package com.winterchen.hadoopdemo.controller;


import com.winterchen.hadoopdemo.service.HDFSService;
import com.winterchen.hadoopdemo.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.hadoop.fs.BlockLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Api(tags = "hdfs api")
@RestController
@RequestMapping("/api/v1/hdfs")
public class HDFSController {

    @Autowired
    private HDFSService hdfsService;

    @ApiOperation("test")
    @GetMapping("/hello")
    public APIResponse test() {
        return APIResponse.success();
    }


    @ApiOperation("create folder")
    @PostMapping("/folder")
    public APIResponse<Boolean> createFolder(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path
    ){
        return APIResponse.success(hdfsService.makeFolder(path));
    }

    @ApiOperation("check file is exist")
    @PostMapping("/exist")
    public APIResponse<Boolean> existFile(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path
    ) {
        return APIResponse.success(hdfsService.existFile(path));
    }

    @ApiOperation("read catalog")
    @GetMapping("/catalog")
    public APIResponse<List<Map<String, Object>>> readCatalog(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path
    ) {
        return APIResponse.success(hdfsService.readCatalog(path));
    }

    @ApiOperation("create file")
    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public APIResponse<Boolean> createFile(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path,
            @ApiParam(name = "file", required = true)
            @RequestParam(name = "file", required = true)
                    MultipartFile file
    ) {
        return APIResponse.success(hdfsService.createFile(path, file));
    }

    @ApiOperation("read file content")
    @PostMapping("/file/content")
    public APIResponse<String> readFileContent(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path
    ) {
        return APIResponse.success(hdfsService.readFileContent(path));
    }

    @ApiOperation("list file")
    @GetMapping("/file/list")
    public APIResponse<List<Map<String, Object>>> listFile(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path
    ) {
        return APIResponse.success(hdfsService.listFile(path));
    }

    @ApiOperation("rename file")
    @PutMapping("/file")
    public APIResponse<Boolean> renameFile(
            @ApiParam(name = "oldName", required = true)
            @RequestParam(name = "oldName", required = true)
                    String oldName,
            @ApiParam(name = "newName", required = true)
            @RequestParam(name = "newName", required = true)
                    String newName
    ) {
        return APIResponse.success(hdfsService.renameFile(oldName, newName));
    }

    @ApiOperation("delete file")
    @DeleteMapping("/file")
    public APIResponse<Boolean> deleteFile(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path
    ) {
        return APIResponse.success(hdfsService.deleteFile(path));
    }

    @ApiOperation("upload file")
    @PostMapping("/file/upload")
    public APIResponse<Boolean> uploadFile(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path,
            @ApiParam(name = "uploadPath", required = true)
            @RequestParam(name = "uploadPath", required = true)
                    String uploadPath
    ) {
        return APIResponse.success(hdfsService.uploadFile(path, uploadPath));
    }

    @ApiOperation("download file")
    @PostMapping("/file/download")
    public APIResponse<Boolean> downloadFile(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path,
            @ApiParam(name = "downloadPath", required = true)
            @RequestParam(name = "downloadPath", required = true)
                    String downloadPath
    ) {
        return APIResponse.success(hdfsService.downloadFile(path, downloadPath));
    }

    @ApiOperation("copy file")
    @PostMapping("/file/copy")
    public APIResponse<Boolean> copyFile(
            @ApiParam(name = "sourcePath", required = true)
            @RequestParam(name = "sourcePath", required = true)
                    String sourcePath,
            @ApiParam(name = "targetPath", required = true)
            @RequestParam(name = "targetPath", required = true)
                    String targetPath
    ) {
        return APIResponse.success(hdfsService.copyFile(sourcePath, targetPath));
    }

    @ApiOperation("open file to types")
    @PostMapping("file/to-bytes")
    public APIResponse<Byte[]> openFileToBytes(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path
    ) {
        return APIResponse.success(hdfsService.openFileToBytes(path));
    }

    @ApiOperation("get file block locations")
    @GetMapping("/file/block-localtions")
    public APIResponse<BlockLocation[]> getFileBlockLocations(
            @ApiParam(name = "path", required = true)
            @RequestParam(name = "path", required = true)
                    String path
    ){
        return APIResponse.success(hdfsService.getFileBlockLocations(path));
    }
}
