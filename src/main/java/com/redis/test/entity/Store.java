package com.redis.test.entity;

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
public class Store implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String storeName;

    private String lat;

    private String lon;

    private Date createdTime;

    private String createdUser;

    private Date updatedTime;

    private String updatedUser;

    private Boolean isDeleted;


}
