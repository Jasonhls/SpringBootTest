package com.redis.test.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.redis.test.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.redis.test.req.StoreQueryReq;
import com.redis.test.req.StoreReq;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
public interface StoreMapper extends BaseMapper<Store> {

	IPage<Store> getStoreReqPage(Page<Store> page, @Param("storeQueryReq") StoreQueryReq storeQueryReq);
}
