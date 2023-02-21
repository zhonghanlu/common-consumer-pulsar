package com.ymxc.webmvc;

public class Query {
    /**
     * 数据偏移 (数据库用)
     */
    private int offset;

    /**
     * 每页记录
     */
    private int limit = 50;

    /**
     * 当前页
     */
    private int page = 1;

    /**
     * @return the offset
     */
    public int getOffset() {
        offset = (page - 1) * limit;
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        if (page <= 0) {
            page = 1;
        }
        this.page = page;
    }

}
