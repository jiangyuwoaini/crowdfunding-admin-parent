package com.lblz.crowd.beans;

import java.util.Objects;

/**
 * @author lblz
 * @deacription 分页工具类
 * @date 2021/11/6 21:47
 **/
public class PageInfoResult{

    /**
     * 当前页 默认为1
     */
    private Integer currentPage;

    /**
     * 每页总数据 默认为10
     */
    private Integer pageCount;

    /**
     * 总页数量
     */
    private Integer countPage;

    /**
     * 列表总数据
     */
    private Integer size;

    public Integer getCurrentPage() {
        if(Objects.isNull(currentPage)){
            return 1;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageCount() {
        if(Objects.isNull(pageCount)) {
            return 10;
        }
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCountPage() {
        return countPage;
    }

    public void setCountPage(Integer countPage) {
        this.countPage = countPage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
