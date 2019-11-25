package com.leyou.item.controller;

import com.alibaba.fastjson.JSON;
import com.leyou.item.pojo.CategoryTree;
import com.leyou.item.service.CategoryService;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("list")
    public ResponseEntity<List<Category>> queryCategoryListByParentId(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        try {
            if (null == pid || pid.longValue() < 0) {
                // pid为null或者小于等于0，响应400
                return ResponseEntity.badRequest().build();
            }
            // 执行查询操作
            List<Category> categoryList = this.categoryService.queryCategoryListByParentId(pid);
            if (CollectionUtils.isEmpty(categoryList)) {
                // 返回结果集为空，响应404
                return ResponseEntity.notFound().build();
            }
            // 响应200
            return ResponseEntity.ok(categoryList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 根据品牌id查询商品分类
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid) {
        List<Category> list = this.categoryService.queryByBrandId(bid);
        if (null == list || list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    /**
     * 根据商品分类id查询名称
     * @param ids
     * @return
     */
    @GetMapping("names")
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids") List<Long> ids) {
        List<String> list = this.categoryService.queryNameByIds(ids);
        if (null == list | list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("tree")
    public ResponseEntity<String> getCategoryTree() {
        try {
            List<Category> categoryList = this.categoryService.queryCategoryList();
            if (CollectionUtils.isEmpty(categoryList)) {
                return ResponseEntity.notFound().build();
            }
            List<CategoryTree> categoryTreeList = new ArrayList<CategoryTree>();
            for (Category category : categoryList) {
                CategoryTree categoryTree = new CategoryTree();
                categoryTree.setId(category.getId());
                categoryTree.setIsParent(category.getIsParent());
                categoryTree.setName(category.getName());
                categoryTree.setParentId(category.getParentId());
                categoryTree.setSort(category.getSort());
                categoryTreeList.add(categoryTree);
            }
            List<CategoryTree> categoryTrees = parseCategoryTree(categoryTreeList);
            int code = 20000;
            String tree = JSON.toJSON(categoryTrees).toString();
            String res = "{\"code\":" + code + ", \"item\":" + tree + "}";
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    public List<CategoryTree> parseCategoryTree(List<CategoryTree> list) {
        List<CategoryTree> result = new ArrayList<CategoryTree>();

        // 获取第一级节点
        for (CategoryTree category : list) {
            if (0 == category.getParentId()) {
                result.add(category);
            }
        }

        // 递归获取子节点
        for (CategoryTree parent : list) {
            parent = recursiveTree(parent, list);
        }
        return result;
    }

    public static CategoryTree recursiveTree(CategoryTree parent, List<CategoryTree> list) {
        for (CategoryTree category : list) {
            try {
                if (parent.getId() == category.getParentId()) {
                    category = recursiveTree(category, list);
                    parent.getChildren().add(category);
                }
            } catch (Exception e) {
                System.out.println(parent.getName());
                System.out.println(category.getName());
//                System.out.println(category);
                e.printStackTrace();
                System.exit(0);
            }
        }
        return parent;
    }

}
