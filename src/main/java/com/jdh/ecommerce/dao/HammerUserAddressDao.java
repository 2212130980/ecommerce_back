package com.jdh.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jdh.ecommerce.entity.HammerUserAddressEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HammerUserAddressDao extends BaseMapper<HammerUserAddressEntity> {

    @Insert("INSERT INTO hammer_user_address ( id,user_id, user_address, remake, consignee, phone,checked ) VALUES ( #{id} ,#{userId},#{userAddress}  , #{remake} ,#{consignee} , #{phone} ,#{checked}  ) ")
    Boolean addAddress(HammerUserAddressEntity hammerUserAddressEntity);

    @Update("update hammer_user_address set checked = 0 where user_id = #{userId} ")
    Boolean updAddress(Integer userId);
}
