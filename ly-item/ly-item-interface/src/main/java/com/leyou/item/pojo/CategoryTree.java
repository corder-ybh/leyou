package com.leyou.item.pojo;

import java.util.List;

public class CategoryTree extends Category{

    private List<CategoryTree> children;

    public List<CategoryTree> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTree> children) {
        this.children = children;
    }

}
