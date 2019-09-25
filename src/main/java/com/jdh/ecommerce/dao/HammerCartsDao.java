package com.jdh.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdh.ecommerce.bean.CartDto;
import com.jdh.ecommerce.entity.HammerCartsEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HammerCartsDao extends BaseMapper<HammerCartsEntity> {


    IPage<CartDto> selMyCart(Page page, @Param("id") Integer id,@Param("cartId")Integer [] cartId);

    @Insert("INSERT INTO hammer_carts  ( id, user_id, product_id )  VALUES  ( null, #{userId} , #{productId}  )")
    Boolean addMyCart(HammerCartsEntity hammerCartsEntity);
}
