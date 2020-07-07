package com.redis.test.controller;


import com.redis.test.entity.Spu;
import com.redis.test.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

	@Autowired
	private SpuService spuService;

	@PostMapping
	public void addSpu(@RequestBody Spu spu) {
		spuService.addSpu(spu);
	}

	@GetMapping(value = "/updateSpuIndex")
	public void updateSpuIndex(Integer stoerId) {
		spuService.updateSpuIndex(stoerId);
	}
}

