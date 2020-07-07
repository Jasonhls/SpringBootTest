package com.redis.test.mapstruct;

import com.redis.test.entity.Store;
import com.redis.test.req.StoreReq;
import org.mapstruct.Mapper;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-12 09:40
 **/
@Mapper(componentModel = "spring")
public interface StoreMapstruct {
	StoreReq storeToStoreReq(Store store);
}
