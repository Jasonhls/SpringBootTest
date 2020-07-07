package com.redis.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.redis.test.entity.Sku;
import com.redis.test.entity.Spu;
import com.redis.test.mapper.SpuMapper;
import com.redis.test.mapstruct.SkuMapstruct;
import com.redis.test.mapstruct.SpuMapstruct;
import com.redis.test.req.StoreReq;
import com.redis.test.req.StoreSkuReq;
import com.redis.test.req.StoreSpuReq;
import com.redis.test.service.SkuService;
import com.redis.test.service.SpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.redis.test.constant.StoreConstant.Store_INDEX;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

	@Autowired
	private SpuMapper spuMapper;
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	@Autowired
	private SpuMapstruct spuMapstruct;
	@Autowired
	private SkuService skuService;
	@Autowired
	private SkuMapstruct skuMapstruct;

	@Override
	public void addSpu(Spu spu) {
		spu.setCreatedTime(new Date());
		spu.setCreatedUser("helisen");
		spuMapper.insert(spu);
	}

	@Override
	public List<Spu> getSpuListByStoreIds(List<Integer> storeIds) {
		QueryWrapper<Spu> qw = new QueryWrapper<>();
		qw.lambda().in(Spu::getStoreId, storeIds).eq(Spu::getIsDeleted, false).orderByDesc(Spu::getCreatedTime);
		List<Spu> spus = spuMapper.selectList(qw);
		return spus;
	}

	@Override
	public void updateSpuIndex(Integer storeId) {
		StoreReq storeReq = new StoreReq();
		storeReq.setId(storeId);
		List<StoreSpuReq> storeSpuReqList = getStoreSpuReqListByStoreId(storeId);
		if(com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isNotEmpty(storeSpuReqList)) {
			storeReq.setSpus(storeSpuReqList);
			updateSpusIndex(storeReq);
		}
//		List<Integer> spuIds = storeSpuReqList.stream().map(StoreSpuReq::getId).collect(Collectors.toList());
//		List<StoreSkuReq> storeSkuReqList = getStoreSkuReqListBySpuIds(spuIds);
//		if(!CollectionUtils.isEmpty(storeSpuReqList)) {
//			if(!CollectionUtils.isEmpty(storeSkuReqList)) {
//				Map<Integer, List<StoreSkuReq>> skuMap = storeSkuReqList.stream().collect(Collectors.groupingBy(StoreSkuReq::getSpuId));
//				storeSpuReqList.stream().forEach(a -> {
//					a.setSkus(skuMap.get(a.getId()));
//				});
//			}
//			storeReq.setSpus(storeSpuReqList);
//		}
//		updateSpusIndex(storeReq);
	}

	private void updateSpusIndex(StoreReq storeReq) {
		try {
			UpdateRequest updateRequest = new UpdateRequest().index(Store_INDEX).type("_doc").id(String.valueOf(storeReq.getId()));
			updateRequest.parent(String.valueOf(storeReq.getId()));
			String jsonString = JSONObject.toJSONString(storeReq);
			updateRequest.doc(jsonString, XContentType.JSON);
			updateRequest.opType();
			restHighLevelClient.update(updateRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<StoreSpuReq> getStoreSpuReqListByStoreId(Integer storeId) {
		List<Integer> list = new ArrayList<>(1);
		list.add(storeId);
		List<Spu> spuList = getSpuListByStoreIds(list);
		if(CollectionUtils.isEmpty(spuList)) {
			return null;
		}
		return spuMapstruct.spuListToSpuReqList(spuList);
	}

	private List<StoreSkuReq> getStoreSkuReqListBySpuIds(List<Integer> spuIds) {
		if(CollectionUtils.isEmpty(spuIds)) {
			return null;
		}
		List<Sku> skuList = skuService.getSkuListBySpuIds(spuIds);
		if(CollectionUtils.isEmpty(skuList)) {
			return null;
		}
		return skuMapstruct.skuListToSkuReqList(skuList);
	}
}
