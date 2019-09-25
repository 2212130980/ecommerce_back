package com.jdh.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jdh.ecommerce.entity.HammerUserEntity;


public interface HammerUserService extends IService<HammerUserEntity> {

    IPage<HammerUserEntity> getAllRoles(Page<HammerUserEntity> page, QueryWrapper queryWrapper);

    boolean register(HammerUserEntity hammerUserEntity);
}

