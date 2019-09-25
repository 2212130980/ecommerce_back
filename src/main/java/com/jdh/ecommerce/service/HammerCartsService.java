package com.jdh.ecommerce.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jdh.ecommerce.bean.CartDto;
import com.jdh.ecommerce.entity.HammerCartsEntity;

public interface HammerCartsService extends IService<HammerCartsEntity> {

    IPage<CartDto> getAllRoles(Page<CartDto> page, Integer id,Integer [] cartId);

    Boolean addMyCart(HammerCartsEntity hammerCartsEntity);
}

