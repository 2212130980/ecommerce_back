package com.jdh.ecommerce.bean;

import com.jdh.ecommerce.entity.HammerCartsEntity;
import com.jdh.ecommerce.entity.HammerProductEntity;
import lombok.Data;

import java.util.List;
@Data
public class CartDto extends HammerCartsEntity {

    List<HammerProductEntity> hammerProductEntities;

    @Override
    public String toString() {
        return "CartDto{" +
                "list=" + hammerProductEntities +
                "} " + super.toString()+"\n";
    }
}
