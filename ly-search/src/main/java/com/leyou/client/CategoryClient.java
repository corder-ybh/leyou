package com.leyou.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}
