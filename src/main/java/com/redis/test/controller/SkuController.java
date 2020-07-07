package com.redis.test.controller;


import com.redis.test.entity.Sku;
import com.redis.test.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/sku")
public class SkuController {
	@Autowired
	private SkuService skuService;

	@PostMapping
	public void addSku(@RequestBody Sku sku) {
		skuService.addSku(sku);
	}
}

