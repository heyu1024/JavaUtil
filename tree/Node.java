package com.heyu.tree;

import java.util.List;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月23日 17:22:00
 */
public class Node {
    private Integer id;
    private Integer parentId;
    private String name;
    private String code;
    private Integer level;
    private List<Node> children;

    public Node() {
    }

    public Node(Integer id, Integer parentId, String name, String code, Integer level) {
        super();
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.code = code;
        this.level = level;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

}
