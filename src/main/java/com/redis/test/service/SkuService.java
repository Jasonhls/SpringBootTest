package com.redis.test.service;

import com.redis.test.entity.Sku;
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
public interface SkuService extends IService<Sku> {

	void addSku(Sku sku);

	List<Sku> getSkuListBySpuIds(List<Integer> spuIds);
}
