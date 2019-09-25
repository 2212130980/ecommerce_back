package com.jdh.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdh.ecommerce.dao.HammerUserAddressDao;
import com.jdh.ecommerce.entity.HammerUserAddressEntity;
import com.jdh.ecommerce.service.HammerUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("hammerUserAddressService")
public class HammerUserAddressServiceImpl extends ServiceImpl<HammerUserAddressDao, HammerUserAddressEntity> implements HammerUserAddressService {
    @Autowired
    HammerUserAddressDao hammerUserAddressDao;

    @Override
    public IPage<HammerUserAddressEntity> getAllRoles(Page<HammerUserAddressEntity> page, QueryWrapper queryWrapper) {
        return hammerUserAddressDao.selectPage(page, queryWrapper);
    }

    @Override
    public Boolean addAddress(HammerUserAddressEntity hammerUserAddressEntity) {
        return hammerUserAddressDao.addAddress(hammerUserAddressEntity);
    }

    @Override
    public Boolean updAddress(Integer userId) {
        return hammerUserAddressDao.updAddress(userId);
    }

}