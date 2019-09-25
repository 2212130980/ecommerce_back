package com.jdh.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdh.ecommerce.bean.CollectDto;
import com.jdh.ecommerce.dao.HammerCollectDao;
import com.jdh.ecommerce.entity.HammerCollectEntity;
import com.jdh.ecommerce.service.HammerCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("hammerCollectService")
public class HammerCollectServiceImpl extends  ServiceImpl<HammerCollectDao, HammerCollectEntity> implements HammerCollectService {

    @Autowired
    HammerCollectDao  hammerCollectDao;

    @Override
    public IPage<HammerCollectEntity> getAllRoles(Page<HammerCollectEntity> page, QueryWrapper queryWrapper) {
        return hammerCollectDao.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<CollectDto> getMyCollect(Page<CollectDto> page,Integer id) {
        return hammerCollectDao.list(page,id);
    }

    @Override
    public Boolean addMyCollect(HammerCollectEntity hammerCollectEntity) {
        return hammerCollectDao.addMyCollect(hammerCollectEntity);
    }
}