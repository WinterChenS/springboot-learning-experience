package com.winterchen.hadoopdemo.service;


import org.apache.hadoop.fs.BlockLocation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface HDFSService {

    boolean makeFolder(String path);

    boolean existFile(String path);

    List<Map<String, Object>> readCatalog(String path);

    boolean createFile(String path, MultipartFile file);

    String readFileContent(String path);

    List<Map<String, Object>> listFile(String path);


    boolean renameFile(String oldName, String newName);

    boolean deleteFile(String path);

    boolean uploadFile(String path, String uploadPath);

    boolean downloadFile(String path, String downloadPath);

    boolean copyFile(String sourcePath, String targetPath);

    byte[] openFileToBytes(String path);

    BlockLocation[] getFileBlockLocations(String path);



}
