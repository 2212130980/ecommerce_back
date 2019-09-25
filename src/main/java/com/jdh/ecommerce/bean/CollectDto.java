package com.jdh.ecommerce.bean;

import com.jdh.ecommerce.entity.HammerCollectEntity;
import com.jdh.ecommerce.entity.HammerProductEntity;
import lombok.Data;

import java.util.List;

@Data
public class CollectDto extends HammerCollectEntity {

    private List<HammerProductEntity>  productEntities;

    @Override
    public String toString() {
        return "CollectDto{" +
                "productEntities=" + productEntities +
                "} " + super.toString()+"\n";
    }
}
