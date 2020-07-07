package com.redis.test.service;

import com.redis.test.entity.Spu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
public interface SpuService extends IService<Spu> {

	void addSpu(Spu spu);

	List<Spu> getSpuListByStoreIds(List<Integer> storeIds);

	void updateSpuIndex(Integer storeId);
}
