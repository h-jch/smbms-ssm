package com.hjc.tools;

public class PageTool {
    //数据总量
    private int totalCount = 0;
    //页面大小
    private int pageSize = 0;
    //总页数
    private int totalPageCount = 1;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            calTotalPageCount();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    private void calTotalPageCount() {
        if (totalCount % pageSize == 0) {
            totalPageCount = totalCount / pageSize;
        } else {
            totalPageCount = totalCount / pageSize + 1;
        }

    }
}
