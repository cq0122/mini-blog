package com.site.blog.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
public class PageResult implements Serializable {

    private static final int PAGE_WINDOW_SIZE = 5;

    //总记录数
    private long totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int currPage;
    //列表数据
    private List<?> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageResult(List<?> list, long totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
        if (this.totalPage > 0) {
            this.currPage = Math.max(1, Math.min(currPage, this.totalPage));
        } else {
            this.currPage = Math.max(1, currPage);
        }
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageStart() {
        if (totalPage <= 0) {
            return 1;
        }
        if (totalPage <= PAGE_WINDOW_SIZE) {
            return 1;
        }

        int currentPage = Math.max(1, Math.min(currPage, totalPage));
        int halfWindow = PAGE_WINDOW_SIZE / 2;
        if (currentPage <= halfWindow + 1) {
            return 1;
        }
        if (currentPage + halfWindow >= totalPage) {
            return totalPage - PAGE_WINDOW_SIZE + 1;
        }
        return currentPage - halfWindow;
    }

    public int getPageEnd() {
        if (totalPage <= 0) {
            return 0;
        }
        return Math.min(totalPage, getPageStart() + PAGE_WINDOW_SIZE - 1);
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}
