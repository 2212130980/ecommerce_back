package com.jdh.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdh.ecommerce.bean.CollectDto;
import com.jdh.ecommerce.entity.HammerCollectEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HammerCollectDao extends BaseMapper<HammerCollectEntity> {
    IPage<CollectDto> list(Page page, @Param("id") Integer id);

    @Insert("INSERT INTO hammer_collect ( id, user_id, product_id ) VALUES ( null, #{userId} , #{productId}  ) ")
    Boolean addMyCollect(HammerCollectEntity hammerCollectEntity);
}
