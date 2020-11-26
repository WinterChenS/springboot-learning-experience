package com.winterchen.hadoopdemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/25 5:33 下午
 * @description workflow任务请求
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@ApiModel
public class WorkflowRequest {

    @ApiModelProperty("workflow名称")
    private String wfName;
    @ApiModelProperty("workflow路径")
    private String wfPath;
    @ApiModelProperty("执行的sql")
    private String sql;
    @ApiModelProperty("回调编号")
    private String callbackId;

}
