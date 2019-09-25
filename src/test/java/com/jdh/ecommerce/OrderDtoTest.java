package com.jdh.ecommerce;

import com.jdh.ecommerce.service.HammerOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDtoTest {
    @Autowired
    HammerOrderService hammerOrderService;


    @Test
    public void test() {
    /*    Page page = new Page<>(1, 10);
        List<OrderDto> records = hammerOrderService.selOrderDto(page, 1).getRecords();
        for (OrderDto order : records) {
            System.out.println(order);
        }*/


    }


}
