package com.jdh.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdh.ecommerce.bean.OrderDto;
import com.jdh.ecommerce.entity.HammerOrderEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HammerOrderDao extends BaseMapper<HammerOrderEntity> {

    @Insert("INSERT INTO `hammer_order`(`id`, `user_id`, `user_name`, `create_time`, `cost`, `user_address`, `order_number`,`phone`) " +
            "VALUES (null, #{userId} , #{userName}, now(), #{cost} , #{userAddress} , #{orderNumber} ,#{phone} )")
    Boolean addOrder(HammerOrderEntity hammerOrderEntity);

    IPage<OrderDto> selOrderDto(Page page, @Param("userId") Integer userId);

    @Update("update hammer_order set " +
            "logistics_status = logistics_status+1" +
            " where logistics_status <4 and logistics_status >=2")
    Integer updateStatus();
}
