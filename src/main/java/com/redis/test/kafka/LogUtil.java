package com.redis.test.kafka;

import com.redis.test.constant.KafkaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: helisen
 * @create: 2020-06-08 16:20
 **/
@Component
public class LogUtil {
	@Autowired
	private KafkaProducer kafkaProducer;

	/**
	 * 发送日志
	 * @param obejct
	 */
	public void sendLog(Object obejct){
		kafkaProducer.sendMessage(KafkaConstant.LOG_TOPIC, obejct);
	}

	/**
	 * 发送请求参数日志
	 * @param object
	 */
	public void sendRequestLog(Object object){
		kafkaProducer.sendMessage(KafkaConstant.REQUEST_LOG_TOPIC, object);
	}
}
