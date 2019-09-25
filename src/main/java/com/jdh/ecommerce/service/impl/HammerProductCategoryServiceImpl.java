package com.jdh.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdh.ecommerce.dao.HammerProductCategoryDao;
import com.jdh.ecommerce.entity.HammerProductCategoryEntity;
import com.jdh.ecommerce.service.HammerProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("hammerProductCategoryService")
public class HammerProductCategoryServiceImpl extends ServiceImpl<HammerProductCategoryDao, HammerProductCategoryEntity> implements HammerProductCategoryService {
    @Autowired
    HammerProductCategoryDao hammerProductCategoryDao;


    @Override
    public IPage<HammerProductCategoryEntity> getAllRoles(Page<HammerProductCategoryEntity> page, QueryWrapper<HammerProductCategoryEntity> queryWrapper) {
        return hammerProductCategoryDao.selectPage(page,queryWrapper);
    }
}