package com.jdh.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("hammer_product_category")
public class HammerProductCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer parentId;
	/**
	 * 
	 */
	private Integer type;
	/**
	 * 
	 */
	private String name;

}
