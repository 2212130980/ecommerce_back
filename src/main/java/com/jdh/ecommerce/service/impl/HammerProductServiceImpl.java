package com.jdh.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdh.ecommerce.dao.HammerProductDao;
import com.jdh.ecommerce.entity.HammerProductEntity;
import com.jdh.ecommerce.service.HammerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("hammerProductService")
public class HammerProductServiceImpl extends ServiceImpl<HammerProductDao, HammerProductEntity> implements HammerProductService {
    @Autowired
    HammerProductDao hammerProductDao;

    @Override
    public IPage<HammerProductEntity> getAllRoles(Page<HammerProductEntity> page, QueryWrapper queryWrapper) {
        return hammerProductDao.selectPage(page, queryWrapper);
    }

    @Override
    public Integer updateProductRepertory(Integer productRepertory, Integer productId) {
        return hammerProductDao.updateProductRepertory(productRepertory,productId);
    }


}