package com.jdh.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jdh.ecommerce.entity.HammerProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface HammerProductDao extends BaseMapper<HammerProductEntity> {
   // UPDATE `hammer`.`hammer_product` SET `product_name` = '苹果2S'
   // , `category_level1` = '1', `category_level2` =
   // '3', `is_delete` = '1', `product_img` = 'static/i
   // mg/5.png', `product_repertory` = 23, `price` = 1235,
   // `title` = '现货发/ 苹果7中移动Apple/苹果国行正品苹果8 xr' WHERE `id` = 5;
    @Update("update hammer_product set product_repertory = product_repertory- #{productRepertory} where id=#{productId}")
    Integer updateProductRepertory(@Param("productRepertory") Integer productRepertory,@Param("productId") Integer productId);
}
