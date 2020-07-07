package com.redis.test.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

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
public class Spu implements Serializable {


    @TableId(type = IdType.AUTO)
    private Integer id;

    private String spuName;

    private Integer storeId;

    private Date createdTime;

    private String createdUser;

    private Date updatedTime;

    private String updatedUser;

    private Boolean isDeleted;


}
