package com.winterchen.hadoopdemo.model;

import com.winterchen.hadoopdemo.enums.FrequencyTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/25 6:01 下午
 * @description 定时调度任务请求
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@ApiModel
public class CoordinatorRequest {

    @ApiModelProperty("定时调度任务名称")
    private String coordName;
    @ApiModelProperty("定时调度任务文件路径")
    private String coordPath;
    @ApiModelProperty("频率")
    private FrequencyTypeEnum frequencyType;
    @ApiModelProperty("开始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;
    @ApiModelProperty("workflow名称")
    private String wfName;
    @ApiModelProperty("workflow路径")
    private String wfPath;
    @ApiModelProperty("回调编号")
    private String callbackId;


}
