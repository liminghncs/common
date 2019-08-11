package com.tanghuzi.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 分页查询返回对象
 * @author: Liming
 * @date: 2018/11/22 0:26
 * @version 1.0
 **/
public class Pagination<T> {
    private Integer pageSize = 0;//每页显示数量
    private Integer totalCount = 0;//总数量
    private Integer pageNum = 0;//当前页码
    private Integer pageCount = 0;//总页数
    private List<T> list; //记录集合

    /**
     * 获取每页记录数
     * @return
     */
    public Integer getPageSize() {
        return pageSize;
    }
    /**
     * 设置每页记录数
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    /**
     * 获取记录总数
     * @return
     */
    public Integer getTotalCount() {
        return totalCount;
    }
    /**
     * 设置记录总数
     * @param totalCount
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    /**
     * 获取当前页码
     * @return
     */
    public Integer getPageNum() {
        return pageNum;
    }
    /**
     * 设置当前页码
     * @param pageNum
     */
    public void setPageNum(Integer pageNum) {
        pageNum = pageNum;
    }
    /**
     * 获取总的页数
     * @return
     */
    public Integer getPageCount() {
        if (pageSize == 0 || totalCount == 0)
        {
            return 0;
        }
        pageCount = totalCount / pageSize;
        if (totalCount % pageSize > 0)
        {
            pageCount++;
        }
        return pageCount;
    }
    /**
     * 设置总的页数
     * @param pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 获取数据集合
     * @return
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置数据集合
     * @param list
     */
    public void setList(List<T> list) {
        if(list instanceof Page)
        {
            Page page = (Page) list;
            this.pageNum=page.getPageNum();
            this.pageSize=page.getPageSize();
            this.totalCount=(int)page.getTotal();
        }
        this.list = list;
    }
}
