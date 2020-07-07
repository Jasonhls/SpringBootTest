package com.redis.test.mapstruct;

import com.redis.test.entity.Spu;
import com.redis.test.req.StoreSpuReq;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-12 09:47
 **/
@Mapper(componentModel = "spring")
public interface SpuMapstruct {
	List<StoreSpuReq> spuListToSpuReqList(List<Spu> spuList);
}
