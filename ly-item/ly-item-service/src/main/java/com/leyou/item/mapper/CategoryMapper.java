package com.leyou.item.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {
    /**
     * 根据品牌id查询商品分类
     * @param bid
     * @return
     */
    @Select("SELECT tc.* \n" +
            "FROM leyou.tb_category_brand tcb \n" +
            "LEFT JOIN leyou.tb_category tc ON tcb.category_id = tc.id \n" +
            "WHERE tcb.brand_id = #{bid} ")
    List<Category> queryByBrandId(Long bid);

    /**
     * 根据id查名字
     * @param id
     * @return
     */
    @Select("SELECT name FROM tb_category WHERE id = #{id}")
    String queryNameById(Long id);
}
