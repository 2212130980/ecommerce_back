package com.jdh.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("hammer_user")
public class HammerUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    @TableId(value = "id",type = IdType.AUTO)
	private Long id;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String userPassword;
	/**
	 * 
	 */
	private Integer gender;
	/**
	 * 
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	/**
	 * 
	 */
	private String address;

	private Integer type;

	private Integer isDelete;
}
