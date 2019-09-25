package com.jdh.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jdh.ecommerce.entity.HammerProductEntity;


public interface HammerProductService extends IService<HammerProductEntity> {

    IPage<HammerProductEntity> getAllRoles(Page<HammerProductEntity> page, QueryWrapper queryWrapper);

    Integer updateProductRepertory(Integer productRepertory,Integer productId);
}

