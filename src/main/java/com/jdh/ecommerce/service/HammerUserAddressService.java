package com.jdh.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jdh.ecommerce.entity.HammerUserAddressEntity;


public interface HammerUserAddressService extends IService<HammerUserAddressEntity> {

    IPage<HammerUserAddressEntity> getAllRoles(Page<HammerUserAddressEntity> page, QueryWrapper queryWrapper);

    Boolean addAddress(HammerUserAddressEntity hammerUserAddressEntity);

    Boolean updAddress(Integer userId);
}

