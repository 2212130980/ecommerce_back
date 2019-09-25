package com.jdh.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jdh.ecommerce.bean.OrderDetailDto;
import com.jdh.ecommerce.entity.HammerOrderDetailEntity;

import java.util.List;

public interface HammerOrderDetailService extends IService<HammerOrderDetailEntity> {

    IPage<HammerOrderDetailEntity> getAllRoles(Page<HammerOrderDetailEntity> page, QueryWrapper queryWrapper);

    Boolean addOrderDetail(HammerOrderDetailEntity hammerOrderDetailEntity);

    List<OrderDetailDto> selOrderDetail(String orderId);
}

