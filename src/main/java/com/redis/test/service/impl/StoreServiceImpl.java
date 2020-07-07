package com.redis.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.redis.test.entity.Store;
import com.redis.test.mapper.StoreMapper;
import com.redis.test.mapstruct.StoreMapstruct;
import com.redis.test.req.StoreQueryReq;
import com.redis.test.req.StoreReq;
import com.redis.test.service.StoreService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	@Autowired
	private StoreMapstruct storeMapstruct;

	@Override
	public void addStore(Store store) {
		store.setCreatedTime(new Date());
		store.setCreatedUser("helisen");
		storeMapper.insert(store);
	}

	@Override
	public void updateStore(Store store) {
		store.setUpdatedTime(new Date());
		store.setUpdatedUser("helisen");
		this.saveOrUpdate(store);
		createOrUpdateIndex(store.getId());
	}

	private void createOrUpdateIndex(Integer storeId) {
		Store store = storeMapper.selectById(storeId);
		StoreReq storeReq = storeMapstruct.storeToStoreReq(store);
		createOrUpdateStoreIndex(storeReq);
	}



	private void createOrUpdateStoreIndex(StoreReq storeReq) {
		try {
			String id = String.valueOf(storeReq.getId());
			GetRequest getRequest = new GetRequest().index(Store_INDEX).id(id);
			boolean exists = restHighLevelClient.exists(getRequest);
			if(!exists) {
				IndexRequest indexRequest = new IndexRequest().index(Store_INDEX).type("_doc").id(id);
				String jsonString = JSONObject.toJSONString(storeReq);
				indexRequest.source(jsonString, XContentType.JSON);
				indexRequest.opType(DocWriteRequest.OpType.CREATE);
				restHighLevelClient.indexAsync(indexRequest, addListener);
			}else {
				UpdateRequest updateRequest = new UpdateRequest().index(Store_INDEX).type("_doc").id(id);
				String jsonString = JSONObject.toJSONString(storeReq);
				updateRequest.doc(jsonString, XContentType.JSON);
				updateRequest.opType();
				restHighLevelClient.updateAsync(updateRequest, updateListener);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ActionListener<IndexResponse> addListener = new ActionListener<IndexResponse>() {

		@Override
		public void onResponse(IndexResponse indexResponse) {
			logger.info("add store index " + indexResponse.toString());
		}

		@Override
		public void onFailure(Exception e) {
			logger.error("add store index error " + e.getMessage(), e.getCause());
		}
	};

	private ActionListener<UpdateResponse> updateListener = new ActionListener<UpdateResponse>() {

		@Override
		public void onResponse(UpdateResponse updateResponse) {
			logger.info("update store index " + updateResponse.toString());
		}

		@Override
		public void onFailure(Exception e) {
			logger.error("update store index error " + e.getMessage(), e.getCause());
		}
	};

	@Override
	public IPage<Store> getStoreReqPage(StoreQueryReq storeQueryReq) {
		Page<Store> page = new Page<>(storeQueryReq.getCurrent(), storeQueryReq.getSize());
		return storeMapper.getStoreReqPage(page, storeQueryReq);
	}

	@Override
	public List<StoreReq> getAroundStores(StoreQueryReq storeQueryReq) {
		try {
			SearchRequest searchRequest = new SearchRequest();
			SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
			sourceBuilder.from(storeQueryReq.getCurrent());
			sourceBuilder.size(storeQueryReq.getSize());
			if(StringUtils.isNoneBlank(storeQueryReq.getDistance())) {
				sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders
						.matchAllQuery())
						.filter(
								QueryBuilders.geoDistanceQuery("storeLocation")
									.distance(storeQueryReq.getDistance(), DistanceUnit.KILOMETERS)
									.point(Double.valueOf(storeQueryReq.getLon()), Double.valueOf(storeQueryReq.getLon()))
						)
				);
			}else {
				sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.matchAllQuery())
					.filter(QueryBuilders.geoDistanceQuery("storeLocation")
							.distance("60", DistanceUnit.KILOMETERS)));
			}
			if(StringUtils.isNoneBlank(storeQueryReq.getKeyWord())) {
				sourceBuilder.query(QueryBuilders.fuzzyQuery("storeName", storeQueryReq.getKeyWord()));
			}



			searchRequest.source(sourceBuilder);
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			return analysisReponse(searchResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<StoreReq> analysisReponse(SearchResponse searchResponse) {
		return null;
	}
}
