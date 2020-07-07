package com.redis.test.elasticsearch;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @description:
 * @author: helise	n
 * @create: 2020-06-08 15:50
 **/
@Configuration
public class EsConfiguration implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {
	@Value("${elasticsearch.url}")
	private String url;
	private RestClient client;
	private RestHighLevelClient restHighLevelClient;

	private static int connectionTimeOut = 1000; //连接超时时间
	private static int socketTimeOut = 30000;  // 连接超时时间
	private static int connectionRequestTimeOut = 500; //获取连接的超时时间

	private static int maxConnectionNum = 100; //最大连接数
	private static int maxConnectionPerRoute = 100; //最大路由连接数

	@Override
	public void afterPropertiesSet() throws Exception {
		initClient();
	}

	private void initClient() {
		if(StringUtils.isBlank(url)) {
			throw new RuntimeException("elasticsearch url is not exit");
		}
		String[] nodes = url.split(",");
		HttpHost[] httpHosts = new HttpHost[nodes.length];
		for (int i = 0; i < httpHosts.length; i++) {
			String[] split = nodes[i].split(":");
			HttpHost httpHost = new HttpHost(split[0], Integer.parseInt(split[1]), "http");
			httpHosts[i] = httpHost;
		}
		RestClientBuilder builder = RestClient.builder(httpHosts);
		// 异步httpclient连接延时配置
		builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
			@Override
			public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
				requestConfigBuilder.setConnectTimeout(connectionTimeOut);
				requestConfigBuilder.setSocketTimeout(socketTimeOut);
				requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
				return requestConfigBuilder;
			}
		});
		// 异步httpclient连接数配置
		builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
			@Override
			public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
				httpClientBuilder.setMaxConnTotal(maxConnectionNum);
				httpClientBuilder.setMaxConnPerRoute(maxConnectionPerRoute);
				return httpClientBuilder;
			}
		});
		restHighLevelClient = new RestHighLevelClient(builder);
		client = restHighLevelClient.getLowLevelClient();
	}

	@Override
	public void destroy() throws Exception {
		try {
			if(client != null) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RestHighLevelClient getObject() throws Exception {
		return restHighLevelClient;
	}

	@Override
	public Class<?> getObjectType() {
		return RestHighLevelClient.class;
	}
}
