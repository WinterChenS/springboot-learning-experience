package com.winterchen.webservice.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Optional;

/**
 * @author winterchen
 * @date 2020/11/7 1:20 下午
 */
@Data
@ApiModel("基础分页")
public class BasePage {


    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", required = false, example = "1")
    private Integer pageNum;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量", required = false, example = "10")
    private Integer pageSize;

    /**
     * 获取offset
     * @return
     */
    public Integer getOffset() {
        return (Optional.ofNullable(pageNum).orElse(1) - 1) * Optional.ofNullable(pageSize).orElse(0);
    }
    
    public BasePage createBasePage() {
    	return new BasePage(Optional.ofNullable(pageNum).orElse(1) , Optional.ofNullable(pageSize).orElse(1));
    }
    
	public BasePage(Integer pageNum , Integer pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

    public Integer getPageNum() {
        return Optional.ofNullable(pageNum).orElse(1);
    }

    public Integer getPageSize() {
        return Optional.ofNullable(pageSize).orElse(10);
    }

    public BasePage() {
		super();
	}

}
