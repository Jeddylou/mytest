package cn.itcast.domain;

import java.util.List;

public class PageBean<T> {
    private int totalCount;//总记录数
    private int totalpage;//总页码
    private List<T> list;//每页数据
    private int currentPage;//当前页码
    private int rows;//每页显示记录数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalpage=" + totalpage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
