package com.jdh.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jdh.ecommerce.bean.OrderDetailDto;
import com.jdh.ecommerce.entity.HammerOrderDetailEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HammerOrderDetailDao extends BaseMapper<HammerOrderDetailEntity> {

    @Insert("INSERT INTO `hammer_order_detail`(`id`, `order_id`, `product_id`, `quantity`, `cost`, `pay_status`, `logistics_status`) " +
            "VALUES (null, #{orderId}, #{productId} , #{quantity} , #{cost}, #{payStatus} , #{logisticsStatus} )")
    Boolean addOrderDetail(HammerOrderDetailEntity hammerOrderDetailEntity);

    List<OrderDetailDto> selOrderDetail(String orderId);
}