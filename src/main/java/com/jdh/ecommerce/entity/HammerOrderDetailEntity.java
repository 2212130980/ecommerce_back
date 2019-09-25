package com.jdh.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("hammer_order_detail")
public class HammerOrderDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;
	/**
	 * 
	 */
	private Integer orderId;
	/**
	 * 
	 */
	private Integer productId;
	/**
	 * 
	 */
	private Integer quantity;
	/**
	 * 
	 */
	private Integer cost;
}
