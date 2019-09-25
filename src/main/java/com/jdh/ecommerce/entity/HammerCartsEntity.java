package com.jdh.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("hammer_carts")
public class HammerCartsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer productId;
	/**
	 * 
	 */
	private Integer cartNum;

}
