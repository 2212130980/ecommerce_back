package com.jdh.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdh.ecommerce.bean.CartDto;
import com.jdh.ecommerce.dao.HammerCartsDao;
import com.jdh.ecommerce.entity.HammerCartsEntity;
import com.jdh.ecommerce.service.HammerCartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hammerCartsService")
public class HammerCartsServiceImpl extends ServiceImpl<HammerCartsDao, HammerCartsEntity> implements HammerCartsService {

    @Autowired
    HammerCartsDao hammerCartsDao;

    @Override
    public IPage<CartDto> getAllRoles(Page<CartDto> page, Integer id,Integer [] cartId) {
        return hammerCartsDao.selMyCart(page, id,cartId);
    }

    @Override
    public Boolean addMyCart(HammerCartsEntity hammerCartsEntity) {
        return hammerCartsDao.addMyCart(hammerCartsEntity);
    }
}