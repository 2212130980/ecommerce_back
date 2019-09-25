package com.jdh.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jdh.ecommerce.bean.CollectDto;
import com.jdh.ecommerce.entity.HammerCollectEntity;


public interface HammerCollectService extends IService<HammerCollectEntity> {

    IPage<HammerCollectEntity> getAllRoles(Page<HammerCollectEntity> page, QueryWrapper queryWrapper);

    IPage<CollectDto> getMyCollect(Page<CollectDto> page,Integer id);

    Boolean addMyCollect(HammerCollectEntity hammerCollectEntity);

}

