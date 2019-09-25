package com.jdh.ecommerce.bean;

import com.jdh.ecommerce.entity.HammerOrderEntity;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends HammerOrderEntity {

    List<OrderDetailDto> orderDetailDtoList;

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderDetailDtoList=" + orderDetailDtoList +
                "} " + super.toString() + "\n";
    }
}
