package com.redis.test.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbMiaosha implements Serializable {


    /**
     * 商品唯一编码
     */
    private String goodsCode;

    /**
     * 商品余量
     */
    private Integer goodsNums;


}
