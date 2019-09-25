package com.jdh.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("hammer_order")
public class HammerOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer cost;
	/**
	 * 
	 */
	private String userAddress;

	private String orderNumber;

	private String phone;
    /**
     * 0：未支付，1：以支付
     */
    private Integer payStatus;
    /**
     * 1：代发货，2：已发货，3：运输中，4：已收货
     */
    private Integer logisticsStatus;
}
