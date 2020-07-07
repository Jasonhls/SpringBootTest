package com.redis.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.redis.test.entity.Sku;
import com.redis.test.mapper.SkuMapper;
import com.redis.test.service.SkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {
	@Autowired
	private SkuMapper skuMapper;

	@Override
	public void addSku(Sku sku) {
		sku.setCreatedTime(new Date());
		sku.setCreatedUser("helisen");
		skuMapper.insert(sku);
	}

	@Override
	public List<Sku> getSkuListBySpuIds(List<Integer> spuIds) {
		QueryWrapper<Sku> qw = new QueryWrapper<>();
		qw.lambda().in(Sku::getSpuId, spuIds).eq(Sku::getIsDeleted, false).orderByDesc(Sku::getCreatedTime);
		List<Sku> skus = skuMapper.selectList(qw);
		return skus;
	}
}
