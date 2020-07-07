package com.redis.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Jason on 2019/3/18.
 */
public interface MiaoshaMapper {
    int update(String goodsCode);

    Integer getGoodsNumByGoodsCode(String goodsCode);

    int add(@Param("goodsCode") String goodsCode, @Param("goodsNums") Integer goodsNums);
}
