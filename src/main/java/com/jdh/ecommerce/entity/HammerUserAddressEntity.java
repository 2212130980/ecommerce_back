package com.jdh.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("hammer_user_address")
public class HammerUserAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type= IdType.AUTO)
	private Long id;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private String userAddress;
	/**
	 * 
	 */
	private String remake;

	private String consignee;
	private String phone;
	private String checked;

}
