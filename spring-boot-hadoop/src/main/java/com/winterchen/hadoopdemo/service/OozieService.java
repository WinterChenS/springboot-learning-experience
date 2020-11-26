package com.winterchen.hadoopdemo.service;

import com.winterchen.hadoopdemo.enums.FrequencyTypeEnum;
import com.winterchen.hadoopdemo.model.CoordinatorRequest;
import com.winterchen.hadoopdemo.model.WorkflowRequest;

/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/23 2:06 下午
 * @description
 **/
public interface OozieService {


    /**
     * @Author chendonghua
     * @Description 提交workflow任务
     * @Date 6:21 下午 2020/11/25
     * @Param [workflowRequest]
     * @return java.lang.String
     **/
    String submitWorkflow(WorkflowRequest workflowRequest);

    /**
     * @Author chendonghua
     * @Description 提交coordinator任务
     * @Date 6:21 下午 2020/11/25
     * @Param [coordinatorRequest]
     * @return java.lang.String
     **/
    String submitCoordinator(CoordinatorRequest coordinatorRequest);

    /**
     * @Author chendonghua
     * @Description 创建并上传sql文件至hdfs
     * @Date 6:21 下午 2020/11/25
     * @Param [sql, sqlPath]
     * @return java.lang.String 文件地址
     **/
    String createSqlFileAndUpload(String sql, String sqlPath);

    /**
     * @Author chendonghua
     * @Description 创建并上传workflow任务脚本文件至hdfs
     * @Date 6:22 下午 2020/11/25
     * @Param [wfName, wfPath, sqlPath, callbackId]
     * @return String 文件地址
     **/
    String createWfFileAndUpload(String wfName, String wfPath, String sqlPath, String callbackId);

    /**
     * @Author chendonghua
     * @Description 创建并上传coordinator定时任务脚本文件至hdfs
     * @Date 6:23 下午 2020/11/25
     * @Param [coordName, coordPath, wfPath, frequencyType, callbackId]
     * @return String 文件地址
     **/
    String createCoordFileAndUpload(String coordName, String coordPath, String wfPath, FrequencyTypeEnum frequencyType, String callbackId);

    /**
     * @Author chendonghua
     * @Description 创建shell脚本并上传
     * @Date 6:41 下午 2020/11/25
     * @Param [shellFileName, shellFilePath]
     * @return String 文件地址
     **/
    String  createShellFileAndUpload(String shellFileName, String shellFilePath);

    /**
     * @Author chendonghua
     * @Description 处理回调
     * @Date 6:24 下午 2020/11/25
     * @Param [targetType, targetId]
     * @return void
     **/
    void executeCallback(String executeType, String taskType, String callbackId);

    /**
     * @Author chendonghua
     * @Description 停止定时调度任务
     * @Date 6:24 下午 2020/11/25
     * @Param [jobId]
     * @return void
     **/
    void killCoordinatorJob(String jobId);

}
