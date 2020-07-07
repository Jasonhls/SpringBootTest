package com.redis.test.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.redis.test.entity.Store;
import com.redis.test.req.StoreQueryReq;
import com.redis.test.req.StoreReq;
import com.redis.test.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private StoreService storeService;

	@PostMapping
	public void addStore(@RequestBody Store store) {
		storeService.addStore(store);
	}

	@PutMapping
	public void updateStore(@RequestBody Store store) {
		storeService.updateStore(store);
	}

	@GetMapping(value = "/getStorePage")
	public IPage<Store> getStoreReqPage(StoreQueryReq storeQueryReq) {
		return storeService.getStoreReqPage(storeQueryReq);
	}

	@GetMapping(value = "/getAroundStores")
	public List<StoreReq> getAroundStores(StoreQueryReq storeQueryReq) {
		return null;
	};


}

