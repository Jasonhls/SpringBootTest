package com.redis.test.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author helisen
 * @since 2020-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Sku implements Serializable {


    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer spuId;

    private String skuName;

    private BigDecimal price;

    private Date createdTime;

    private String createdUser;

    private Date updatedTime;

    private String updatedUser;

    private Boolean isDeleted;


}
