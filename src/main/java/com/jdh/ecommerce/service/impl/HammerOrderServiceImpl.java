package com.jdh.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdh.ecommerce.bean.OrderDto;
import com.jdh.ecommerce.dao.HammerOrderDao;
import com.jdh.ecommerce.entity.HammerOrderEntity;
import com.jdh.ecommerce.service.HammerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("hammerOrderService")
public class HammerOrderServiceImpl extends ServiceImpl<HammerOrderDao, HammerOrderEntity> implements HammerOrderService {
    @Autowired
    HammerOrderDao hammerOrderDao;

    @Override
    public IPage<HammerOrderEntity> getAllRoles(Page<HammerOrderEntity> page, QueryWrapper queryWrapper) {
        return hammerOrderDao.selectPage(page, queryWrapper);
    }

    @Override
    public Boolean addOrder(HammerOrderEntity hammerOrderEntity) {
        return hammerOrderDao.addOrder(hammerOrderEntity);
    }

    @Override
    public IPage<OrderDto> selOrderDto(Page page,Integer userId) {
        return hammerOrderDao.selOrderDto(page,userId);
    }

    @Override
    public Integer updateStatus() {
        return hammerOrderDao.updateStatus();
    }


}