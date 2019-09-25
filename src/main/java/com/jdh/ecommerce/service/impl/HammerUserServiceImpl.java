package com.jdh.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdh.ecommerce.dao.HammerUserDao;
import com.jdh.ecommerce.entity.HammerUserEntity;
import com.jdh.ecommerce.service.HammerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("hammerUserService")
public class HammerUserServiceImpl  extends  ServiceImpl<HammerUserDao, HammerUserEntity> implements HammerUserService {

    @Autowired
    HammerUserDao hammerUserDao;

    @Override
    public IPage<HammerUserEntity> getAllRoles(Page<HammerUserEntity> page, QueryWrapper queryWrapper) {
        return hammerUserDao.selectPage(page, queryWrapper);
    }

    @Override
    public boolean register(HammerUserEntity hammerUserEntity) {
        return hammerUserDao.register(hammerUserEntity);
    }

}