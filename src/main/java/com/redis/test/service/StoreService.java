package com.redis.test.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.redis.test.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import com.redis.test.req.StoreQueryReq;
import com.redis.test.req.StoreReq;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
public interface StoreService extends IService<Store> {

	void addStore(Store store);

	void updateStore(Store store);

	IPage<Store> getStoreReqPage(StoreQueryReq storeQueryReq);

	List<StoreReq> getAroundStores(StoreQueryReq storeQueryReq);
}
