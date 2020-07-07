package com.redis.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.redis.test.mapper.UserMapper;
import com.redis.test.pojo.User;
import com.redis.test.req.UserReq;
import com.redis.test.service.UserService;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-09 17:14
 **/
@Service
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestHighLevelClient restHighLevelClient;
	@Autowired
	private UserMapper userMapper;

	@Override
	public int add(User user) {
		int add = userMapper.add(user);
		User userByUsername = userMapper.getUserByUsername(user.getUsername());
		createOrUpdateIndex(userByUsername);
		return add;
	}

	@Override
	public void createOrUpdateIndex(User user) {
		try {
			GetRequest getRequest = new GetRequest("user_index", "doc", String.valueOf(user.getId()));
			boolean exists = restHighLevelClient.exists(getRequest);
			String jsonStr = JSONObject.toJSONString(user);
			if(exists) {
				UpdateRequest updateRequest = new UpdateRequest("user_index", "doc", String.valueOf(user.getId()));
				updateRequest.doc(jsonStr, XContentType.JSON);
				UpdateResponse update = restHighLevelClient.update(updateRequest);
			}else {
				IndexRequest request = new IndexRequest("user_index", "doc", String.valueOf(user.getId()));
				request.source(jsonStr, XContentType.JSON);
				request.opType(DocWriteRequest.OpType.CREATE);
				restHighLevelClient.indexAsync(request, listener);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
		@Override
		public void onResponse(IndexResponse response) {
			logger.info("成功：" + response.toString());
		}

		@Override
		public void onFailure(Exception e) {
			e.printStackTrace();
			logger.error("失败：" + e.getMessage());
		}
	};


	@Override
	public IPage<User> getUserList(UserReq userReq) {
		SearchRequest searchRequest = new SearchRequest().indices("user_index");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.from(userReq.getCurrent());
		sourceBuilder.size(userReq.getSize());
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("username", userReq.getUsername()).fuzziness(Fuzziness.AUTO);
		sourceBuilder.query(queryBuilder);



		searchRequest.source(sourceBuilder);
		return null;
	}
}
