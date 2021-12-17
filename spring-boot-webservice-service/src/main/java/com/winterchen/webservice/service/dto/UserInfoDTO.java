package com.winterchen.webservice.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;




@ApiModel("用户表")
@Data
@Builder
public class UserInfoDTO implements Serializable {


    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("")
    private String name;

    @ApiModelProperty("")
    private Integer age;

    @ApiModelProperty("")
    private String address;

    @Tolerate
    public UserInfoDTO(){}

}
