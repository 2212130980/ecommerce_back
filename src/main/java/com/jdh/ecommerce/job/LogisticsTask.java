package com.jdh.ecommerce.job;

import com.jdh.ecommerce.service.HammerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class LogisticsTask {

    @Autowired
    HammerOrderService hammerOrderService;

    @Scheduled(fixedRate = 60000 * 5)
    public void test() {
        hammerOrderService.updateStatus();
        System.out.println("--------------");
    }
}
