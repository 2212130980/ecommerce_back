package com.jdh.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jdh.ecommerce.entity.HammerProductCategoryEntity;

public interface HammerProductCategoryService extends IService<HammerProductCategoryEntity> {

    IPage<HammerProductCategoryEntity> getAllRoles(Page<HammerProductCategoryEntity> page, QueryWrapper<HammerProductCategoryEntity> queryWrapper);

}

