package com.redis.test.mapstruct;

import com.redis.test.entity.Sku;
import com.redis.test.req.StoreSkuReq;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-12 10:31
 **/
@Mapper(componentModel = "spring")
public interface SkuMapstruct {
	List<StoreSkuReq> skuListToSkuReqList(List<Sku> skuList);
}
