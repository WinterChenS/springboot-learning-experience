package com.winterchen.webservice.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;


/**
* 用户表
*/
@Data
@TableName("user_info")
@Builder
public class UserInfoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value="id" ,type = IdType.AUTO)
    /**  */
    @TableField("id")
    private Long id;

    /**  */
    @TableField("name")
    private String name;

    /**  */
    @TableField("age")
    private Integer age;

    /**  */
    @TableField("address")
    private String address;


    @Tolerate
    public UserInfoEntity(){}
}
