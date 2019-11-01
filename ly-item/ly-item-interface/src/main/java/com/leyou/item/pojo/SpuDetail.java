package com.leyou.item.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_spu_detail")
public class SpuDetail {
    @Id
    private Long spuId; // 对应的SPU的id
    private String description; // 商品描述
    private String specTemplate; // 商品特殊规格的名称以及可选值模板
    private String Specifications; // 商品的全规格属性
    private String pakcingList; // 包装清单
    private String afterService; // 售后服务

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecTemplate() {
        return specTemplate;
    }

    public void setSpecTemplate(String specTemplate) {
        this.specTemplate = specTemplate;
    }

    public String getSpecifications() {
        return Specifications;
    }

    public void setSpecifications(String specifications) {
        Specifications = specifications;
    }

    public String getPakcingList() {
        return pakcingList;
    }

    public void setPakcingList(String pakcingList) {
        this.pakcingList = pakcingList;
    }

    public String getAfterService() {
        return afterService;
    }

    public void setAfterService(String afterService) {
        this.afterService = afterService;
    }
}
