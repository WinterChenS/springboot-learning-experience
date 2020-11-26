package com.winterchen.hadoopdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/23 3:09 下午
 * @description TODO
 **/
@Getter
@AllArgsConstructor
public enum FrequencyTypeEnum {
    MINUTES("${coord:minutes(%s)}", "分钟执行一次"),
    HOURS("${coord:hours(%s)}", "小时执行一次"),
    DAYS("${coord:days(%s)}", "天执行一次"),
    MONTHS("${coord:months(%s)}", "月执行一次");

    private String expression;

    private String desc;

    public static String getExpressionByName(String name, Integer value) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        if (null == value) {
            value = 1;
        }
        for (FrequencyTypeEnum typeEnum : FrequencyTypeEnum.values()) {
            if (typeEnum.name().equals(name)) {
                return String.format(typeEnum.expression, value);
            }
        }
        return null;
    }

}
