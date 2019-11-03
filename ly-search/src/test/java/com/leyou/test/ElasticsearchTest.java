package com.leyou.test;

import com.leyou.LySearch;
import com.leyou.Repository.GoodsRepository;
import com.leyou.client.GoodsClient;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.SpuBo;
import com.leyou.pojo.Goods;
import com.leyou.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest {
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SearchService searchService;


    public static final Logger logger = LoggerFactory.getLogger(ElasticsearchTest.class);

    @Test
    public void createIndex() {
        // 创建索引
        elasticsearchTemplate.createIndex(Goods.class);
        // 配置映射
        elasticsearchTemplate.putMapping(Goods.class);
    }

    @Test
    public void loadData() {
        // 创建索引
        elasticsearchTemplate.createIndex(Goods.class);
        // 配置映射
        elasticsearchTemplate.putMapping(Goods.class);
        int page = 1;
        int rows = 100;
        int size = 0;
        do {
            // 查询分页数据
            PageResult<SpuBo> result = this.goodsClient.querySpuByPage(page, rows, true, null);
            List<SpuBo> spus = result.getItems();
            size = spus.size();
            // 创建goods集合
            List<Goods> goodsList = new ArrayList<>();
            // 遍历spu
            for (SpuBo spu : spus) {
                try {
                    Goods goods = this.searchService.buildGoods(spu);
                    goodsList.add(goods);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    break;
                }
            }

            this.goodsRepository.saveAll(goodsList);
            page++;
        } while (100 == size);
    }
}
