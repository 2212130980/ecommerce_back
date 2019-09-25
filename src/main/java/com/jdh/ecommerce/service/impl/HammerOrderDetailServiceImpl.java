package com.jdh.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdh.ecommerce.bean.OrderDetailDto;
import com.jdh.ecommerce.dao.HammerOrderDetailDao;
import com.jdh.ecommerce.entity.HammerOrderDetailEntity;
import com.jdh.ecommerce.service.HammerOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("hammerOrderDetailService")
public class HammerOrderDetailServiceImpl extends ServiceImpl<HammerOrderDetailDao, HammerOrderDetailEntity> implements HammerOrderDetailService {
    @Autowired
    HammerOrderDetailDao hammerOrderDetailDao;

    @Override
    public IPage<HammerOrderDetailEntity> getAllRoles(Page<HammerOrderDetailEntity> page, QueryWrapper queryWrapper) {
        return hammerOrderDetailDao.selectPage(page, queryWrapper);
    }

    @Override
    public Boolean addOrderDetail(HammerOrderDetailEntity hammerOrderDetailEntity) {
        return hammerOrderDetailDao.addOrderDetail(hammerOrderDetailEntity);
    }

    @Override
    public List<OrderDetailDto> selOrderDetail(String orderId) {
        return hammerOrderDetailDao.selOrderDetail(orderId);
    }

}