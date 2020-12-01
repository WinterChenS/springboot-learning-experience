package com.winterchen.hadoopdemo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.winterchen.hadoopdemo.constants.OozieConstants;
import com.winterchen.hadoopdemo.enums.FrequencyTypeEnum;
import com.winterchen.hadoopdemo.enums.TaskTypeEnum;
import com.winterchen.hadoopdemo.model.CoordinatorRequest;
import com.winterchen.hadoopdemo.model.OozieConfig;
import com.winterchen.hadoopdemo.model.WorkflowRequest;
import com.winterchen.hadoopdemo.service.OozieService;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.oozie.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;


/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/23 2:06 下午
 * @description
 **/
@Slf4j
@Service
public class OozieServiceImpl implements OozieService {

    @Autowired
    private FileSystem fileSystem;

    private final OozieConfig oozieConfig;

    @Autowired
    public OozieServiceImpl(OozieConfig oozieConfig) {
        this.oozieConfig = oozieConfig;
    }

    @Override
    public String submitWorkflow(WorkflowRequest workflowRequest) {
        try {
            OozieClient oozieClient = new OozieClient(oozieConfig.getUrl());
            oozieClient.setDebugMode(1);
            Path appPath = new Path(fileSystem.getHomeDirectory(), workflowRequest.getWfPath().concat(workflowRequest.getWfName()).concat(".xml"));
            // 创建相关文件

            // 创建并上传sql文件
            String sqlPath = workflowRequest.getWfPath().concat("sql/".concat(workflowRequest.getWfName()).concat("-sql.q"));
            createSqlFileAndUpload(workflowRequest.getSql(), sqlPath);

            // 创建shell脚本
            String shellFileName = workflowRequest.getWfName() + "-shell.sh";
            String shellFilePath = workflowRequest.getWfPath().concat(workflowRequest.getWfName()).concat("/shell/");
            String shellPath = createShellFileAndUpload(shellFileName, shellFilePath);


            // 创建并上传wf脚本文件
            createWfFileAndUpload(workflowRequest.getWfName(), workflowRequest.getWfPath(), sqlPath, workflowRequest.getCallbackId());


            // 创建脚本任务的配置
            Properties prop = oozieClient.createConfiguration();
            prop.setProperty(OozieClient.APP_PATH, appPath.toString());
            prop.setProperty(oozieClient.LIBPATH, oozieConfig.getOozieLibPath());
            prop.setProperty(oozieClient.USE_SYSTEM_LIBPATH, String.valueOf(oozieConfig.isOozieSystemLibPath()));

            /*Set Your Application Configuration*/
            prop.setProperty(OozieConstants.NAME_NODE, oozieConfig.getNameNode());
            prop.setProperty(OozieConstants.JOB_TRACKER,oozieConfig.getJobTracker());
            Path outputPath = new Path(fileSystem.getHomeDirectory(), workflowRequest.getWfPath().concat("output/"));
            prop.setProperty(OozieConstants.JOB_OUTPUT, outputPath.toString());
            prop.setProperty(OozieConstants.JDBC_URL, oozieConfig.getJdbcUrl());
            prop.setProperty(OozieConstants.PASSWORD, StringUtils.isEmpty(oozieConfig.getPassword()) ? "" : oozieConfig.getPassword());
            prop.setProperty(OozieConstants.SQL_INPUT,workflowRequest.getWfPath().concat("sql/"));
            prop.setProperty(OozieConstants.USER_NAME,"admin");
            prop.setProperty(OozieConstants.TASK_TYPE, TaskTypeEnum.WORKFLOW.name());
            prop.setProperty(OozieConstants.SHELL_FILE_NAME,shellFileName);
            prop.setProperty(OozieConstants.SHELL_FILE_PATH, shellPath);
            prop.setProperty(OozieConstants.CALLBACK_ID, workflowRequest.getCallbackId());
            prop.setProperty(OozieConstants.QUEUE_NAME, oozieConfig.getQueueName());

            String jobId = oozieClient.submit(prop);
            oozieClient.start(jobId);


            log.debug("workflow job submitted, jobId = {}", jobId);

            return jobId;
        } catch (OozieClientException e) {
            log.error("workflow任务提交失败" ,e);
        }

        return null;
    }

    @Override
    public String submitCoordinator(CoordinatorRequest coordinatorRequest) {

        try {
            OozieClient oozieClient = new OozieClient(oozieConfig.getUrl());
            oozieClient.setDebugMode(1);
            Path rootPath = new Path(fileSystem.getHomeDirectory(), coordinatorRequest.getCoordPath());
            Path appPath = new Path(fileSystem.getHomeDirectory(), coordinatorRequest.getCoordPath()
                    .concat(coordinatorRequest.getCoordName()).concat(".xml"));
            Path wf = new Path(fileSystem.getHomeDirectory(), coordinatorRequest.getWfPath());
            // 创建相关文件
            // 创建并上传定时调度任务脚本
            createCoordFileAndUpload(coordinatorRequest.getCoordName(),coordinatorRequest.getCoordPath(),
                    wf.toString().concat("/").concat(coordinatorRequest.getWfName()).concat(".xml"),coordinatorRequest.getFrequencyType(), coordinatorRequest.getCallbackId());

            // 创建shell脚本
            String shellFileName = coordinatorRequest.getWfName() + "-shell.sh";
            String shellFilePath = coordinatorRequest.getWfPath().concat(coordinatorRequest.getWfName()).concat("/shell/");
            String shellPath = createShellFileAndUpload(shellFileName, shellFilePath);

            // 创建脚本任务的配置
            Properties prop = oozieClient.createConfiguration();
            prop.setProperty(OozieClient.COORDINATOR_APP_PATH, appPath.toString());
            prop.setProperty(oozieClient.LIBPATH, oozieConfig.getOozieLibPath());
            prop.setProperty(oozieClient.USE_SYSTEM_LIBPATH, String.valueOf(oozieConfig.isOozieSystemLibPath()));
            prop.setProperty(OozieConstants.JOB_TRACKER,oozieConfig.getJobTracker());
            prop.setProperty(OozieConstants.USER_NAME,"admin");
            prop.setProperty(OozieConstants.WORKFLOW_ROOT, rootPath.toString());
            String start = DateUtil.format(DateUtil.parse(coordinatorRequest.getStartTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd'T'HH:mm'Z'");
            prop.setProperty(OozieConstants.START, start);
            String end = DateUtil.format(DateUtil.parse(coordinatorRequest.getEndTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd'T'HH:mm'Z'");
            prop.setProperty(OozieConstants.END, end);
            Path outputPath = new Path(fileSystem.getHomeDirectory(), coordinatorRequest.getWfPath().concat("output/"));
            prop.setProperty(OozieConstants.JOB_OUTPUT, outputPath.toString());
            prop.setProperty(OozieConstants.JDBC_URL, oozieConfig.getJdbcUrl());
            prop.setProperty(OozieConstants.PASSWORD, StringUtils.isEmpty(oozieConfig.getPassword()) ? "" : oozieConfig.getPassword());
            prop.setProperty(OozieConstants.SQL_INPUT,coordinatorRequest.getWfPath().concat("sql/"));
            prop.setProperty(OozieConstants.TASK_TYPE, TaskTypeEnum.COORDINATOR.name());
            prop.setProperty(OozieConstants.SHELL_FILE_NAME,shellFileName);
            prop.setProperty(OozieConstants.SHELL_FILE_PATH, shellPath);
            prop.setProperty(OozieConstants.CALLBACK_ID, coordinatorRequest.getCallbackId());
            prop.setProperty(OozieConstants.QUEUE_NAME, oozieConfig.getQueueName());

            /*Set Your Application Configuration*/
            prop.setProperty(OozieConstants.NAME_NODE, oozieConfig.getNameNode());

            String jobId = oozieClient.submit(prop);

            log.debug("workflow job submitted, jobId = {}", jobId);

            return jobId;
        } catch (OozieClientException e) {
            log.error("workflow任务提交失败" ,e);
        }

        return null;
    }

    @Override
    public String createSqlFileAndUpload(String sql, String sqlPath) {
        Writer writer = null;
        try {
            Path sqlP = new Path(fileSystem.getHomeDirectory(),sqlPath);
            writer = new OutputStreamWriter(fileSystem.create(sqlP));

            writer.write(sql);
            return sqlP.toString();
        } catch (IOException e) {
            log.error("创建sql文件失败", e);
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("关闭流失败", e);
                }
            }
        }
        return null;
    }

    @Override
    public String createWfFileAndUpload(String wfName, String wfPath, String sqlFileName, String callbackId) {
        Writer writer = null;
        try {
            Path wf = new Path(fileSystem.getHomeDirectory(),wfPath.concat(wfName).concat(".xml"));
            writer = new OutputStreamWriter(fileSystem.create(wf));
            String wfApp =
                    "<workflow-app xmlns='uri:oozie:workflow:0.4' name='" + wfName + "'>\n" +
                            "    <start to='my-hive2-action'/>\n" +
                            "    <action name='my-hive2-action'>\n" +
                            "       <hive2 xmlns='uri:oozie:hive2-action:0.1'>\n" +
                            "           <name-node>${nameNode}</name-node>\n" +
                            "           <prepare>\n" +
                            "               <delete path='${jobOutput}'/>\n" +
                            "           </prepare>\n" +
                            "           <configuration>\n" +
                            "                <property>\n" +
                            "                    <name>mapred.compress.map.output</name>\n" +
                            "                    <value>true</value>\n" +
                            "                </property>\n" +
                            "           </configuration>\n" +
                            "           <jdbc-url>${jdbcUrl}</jdbc-url>\n" +
//                            "           <password>${password}</password>\n" +
                            "           <script>" + sqlFileName + "</script>\n" +
                            "           <param>InputDir=${sqlInput}</param>\n" +
                            "           <param>OutputDir=${jobOutput}</param>\n" +
                            "       </hive2>\n" +
                            "    <ok to='success-action'/>\n" +
                            "    <error to='error-action'/>\n" +
                            "    </action>\n" +
                            "    <!-- 成功回调 -->\n" +
                            "    <action name='success-action'>\n" +
                            "        <shell xmlns=\"uri:oozie:shell-action:0.2\">\n" +
                            "            <job-tracker>${jobTracker}</job-tracker>\n" +
                            "            <name-node>${nameNode}</name-node>\n" +
                            "            <configuration>\n" +
                            "                <property>\n" +
                            "                  <name>mapred.job.queue.name</name>\n" +
                            "                  <value>${queueName}</value>\n" +
                            "                </property>\n" +
                            "            </configuration>\n" +
                            "            <exec>${shellFileName}</exec>\n" +
                            "            <argument>${taskType}</argument>\n" +
                            "            <argument>OK</argument>\n" +
                            "            <argument>${callbackId}</argument>\n" +
                            "            <file>${shellFilePath}#${shellFilePath}</file> <!--Copy the executable to compute node's current working directory -->\n" +
                            "        </shell>\n" +
                            "        <ok to='end' />\n" +
                            "        <error to='fail' />\n" +
                            "    </action>\n" +
                            "     \n" +
                            "    <!-- 失败回调 -->\n" +
                            "    <action name='error-action'>\n" +
                            "        <shell xmlns=\"uri:oozie:shell-action:0.2\">\n" +
                            "            <job-tracker>${jobTracker}</job-tracker>\n" +
                            "            <name-node>${nameNode}</name-node>\n" +
                            "            <configuration>\n" +
                            "                <property>\n" +
                            "                  <name>mapred.job.queue.name</name>\n" +
                            "                  <value>${queueName}</value>\n" +
                            "                </property>\n" +
                            "            </configuration>\n" +
                            "            <exec>${shellFileName}</exec>\n" +
                            "            <argument>${taskType}</argument>\n" +
                            "            <argument>FAIL</argument>\n" +
                            "            <argument>${callbackId}</argument>\n" +
                            "            <file>${shellFilePath}#${shellFilePath}</file> <!--Copy the executable to compute node's current working directory -->\n" +
                            "        </shell>\n" +
                            "        <ok to='end' />\n" +
                            "        <error to='fail' />\n" +
                            "    </action>\n" +
                            "    <kill name='fail'>\n" +
                            "        <message>执行脚本失败</message>\n" +
                            "    </kill>\n" +
                            "    <end name='end'/>\n"   +
                            "</workflow-app>";
            writer.write(wfApp);
            return wf.toString();
        } catch (IOException e) {
            log.error("创建workflow文件失败", e);
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("关闭流失败", e);
                }
            }
        }
        return null;
    }

    @Override
    public String createCoordFileAndUpload(String coordName, String coordPath, String wfPath, FrequencyTypeEnum frequencyType, String callbackId) {
        Writer writer = null;
        try {
            Path coord = new Path(fileSystem.getHomeDirectory(),coordPath.concat(coordName).concat(".xml"));
            writer = new OutputStreamWriter(fileSystem.create(coord));
            String frequency = FrequencyTypeEnum.getExpressionByName(frequencyType.name(), 1);
            String wfApp =
                    "<coordinator-app name='" + coordName + "' frequency='" + frequency + "' start='${start}' end='${end}' timezone='Asia/Shanghai'\n" +
                            "                 xmlns='uri:oozie:coordinator:0.4'>\n" +
                            "        <action>\n" +
                            "        <workflow>\n" +
                            "            <app-path>" + wfPath + "</app-path>\n" +
                            "        </workflow>\n" +
                            "    </action>\n" +
                            "</coordinator-app>";
            writer.write(wfApp);
            return coordName.toString();
        } catch (IOException e) {
            log.error("创建coordinator文件失败", e);
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("关闭流失败", e);
                }
            }
        }
        return null;
    }

    @Override
    public String createShellFileAndUpload(String shellFileName, String shellFilePath) {
        Writer writer = null;
        try {
            Path shellPath = new Path(fileSystem.getHomeDirectory(),shellFilePath.concat(shellFileName));
            writer = new OutputStreamWriter(fileSystem.create(shellPath));
            String shell =
                    "#!/bin/bash\n" +
                    "echo 'curl " + oozieConfig.getCallbackUrl() + "';\n" +
                    "curl -X GET " + oozieConfig.getCallbackUrl();
            writer.write(shell);
            return shellPath.toString();
        } catch (IOException e) {
            log.error("创建shell文件失败", e);
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("关闭流失败", e);
                }
            }
        }
        return null;
    }

    @Override
    public void executeCallback(String executeType, String taskType, String callbackId) {
        // TODO
        log.info("回调处理，executeType={}, taskType={}, callbackId={}", executeType, taskType, callbackId);
    }

    @Override
    public void killCoordinatorJob(String jobId) {
        OozieClient oozieClient = new OozieClient(oozieConfig.getUrl());
        oozieClient.setDebugMode(1);
        try {
            oozieClient.kill(jobId);
        } catch (OozieClientException e) {
            log.error("停止定时任务失败", e);
        }
    }
}
