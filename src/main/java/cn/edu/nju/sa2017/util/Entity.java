package cn.edu.nju.sa2017.util;


/**
 * Created by njucjc on 2017/6/17.
 */
public class Entity {

    private Integer page = 1;
    private Integer rows = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
