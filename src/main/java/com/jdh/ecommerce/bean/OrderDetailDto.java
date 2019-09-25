package com.jdh.ecommerce.bean;

import com.jdh.ecommerce.entity.HammerOrderDetailEntity;
import com.jdh.ecommerce.entity.HammerProductEntity;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailDto extends HammerOrderDetailEntity {

    List<HammerProductEntity> list;

    @Override
    public String toString() {
        return "OrderDetailDto{" +
                "list=" + list +
                "} " + super.toString();
    }
}
