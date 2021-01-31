package com.jmh.springboottest.pojo;

import java.util.List;

/**
 * name : jmh
 * time : 2021/1/22 16:16
 * @author snjmh
 */
public class Query {
    /**
     * ids
     */
    private List<Integer> idList;

    /**
     * 操作者名字
     */
    private String name;
    /**
     * 状态（未选着，以选择）
     */
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
