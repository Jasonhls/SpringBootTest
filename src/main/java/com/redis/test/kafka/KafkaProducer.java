package com.redis.test.kafka;

import com.redis.test.threadPool.AsyncProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-08 16:31
 **/
@Component
public class KafkaProducer {
	private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	/**
	 * 异步发送消息
	 * @param topic
	 * @param obj
	 */
	public void sendMessage(String topic, Object obj) {
		AsyncProcessor.executeTask(() -> {
			kafkaTemplate.send(topic, obj);
		});
	}
}
