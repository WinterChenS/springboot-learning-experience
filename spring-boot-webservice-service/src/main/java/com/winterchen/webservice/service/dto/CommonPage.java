package com.winterchen.webservice.service.dto;

import lombok.Data;

import java.util.List;

/**
 * @author winterchen
 * @date 2020/11/7 1:20 下午
 */
@Data
public class CommonPage<T>  implements  java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 当前页码
     */
    private Integer pageNum;
    
    /**
     * 每页数量
     */
    private Integer pageSize;
    
    /**
     * 总页数
     */
    private Long totalPage;
    
    /**
     * 总条数
     */
    private Long total;
    
    /**
     * 分页数据
     */
    private List<T> list;

    private Integer offset;

    private Integer limit;



	public static <T> CommonPage<T> create(List<T> list , long total) {
    	return new CommonPage<T>(null , null , null , total , list);
    }
    
    public static <T> CommonPage<T> create(BasePage basePage , List<T> list , long total) {
    	long totalPage = getTotalPage(basePage.getPageNum() , basePage.getPageSize() , total);
    	return new CommonPage<T>(basePage.getPageNum() , basePage.getPageSize() , totalPage , total , list);
    }
    
    public static <T> CommonPage<T> create(int pageNum , int pageSize , List<T> list , long total) {
    	return new CommonPage<T>(pageNum , pageSize , getTotalPage(pageNum , pageSize , total) , total , list);
    }
    
    public static long getTotalPage(int pageNum , int pageSize , long total) {
    	return ( total - 1) / pageSize + 1;
    }
    
	private CommonPage(Integer pageNum , Integer pageSize , Long totalPage , Long total , List<T> list) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.total = total;
		this.list = list;
	}

	public CommonPage() {
		super();
	}

	public Integer getOffset() {
		if (offset != null) {
			return offset;
		}
		if (pageNum == null || pageSize == null) {
			return 0;
		}
		return (pageNum - 1) * pageSize;
	}

	public Integer getLimit() {
		if (pageNum == null || pageSize == null) {
			return 10;
		}
		return pageSize * pageNum;
	}


}
