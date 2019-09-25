package com.jdh.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jdh.ecommerce.bean.OrderDto;
import com.jdh.ecommerce.entity.HammerOrderEntity;

public interface HammerOrderService extends IService<HammerOrderEntity> {

    IPage<HammerOrderEntity> getAllRoles(Page<HammerOrderEntity> page, QueryWrapper queryWrapper);

    Boolean addOrder(HammerOrderEntity hammerOrderEntity);

    IPage<OrderDto> selOrderDto(Page page,Integer userId);

    Integer updateStatus();
}

