package com.winterchen.hadoopdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/25 6:40 下午
 * @description 任务类型
 **/
@Getter
@AllArgsConstructor
public enum TaskTypeEnum {

    WORKFLOW,COORDINATOR;
}
