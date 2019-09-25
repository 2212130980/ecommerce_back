package com.jdh.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jdh.ecommerce.entity.HammerUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HammerUserDao extends BaseMapper<HammerUserEntity> {

//    INSERT INTO `hammer`.`hammer_user`(`id`, `user_name`, `user_password`, `gender`, `birthday`, `address`) VALUES (1, 'admin', '1', 1, '2019-07-16 10:17:57', '长沙');
    @Insert("insert into hammer_user(`id`, `user_name`, `user_password`)" +
            " values(null,#{userName} ,#{userPassword} )  ")
    boolean register(HammerUserEntity hammerUserEntity);
}
