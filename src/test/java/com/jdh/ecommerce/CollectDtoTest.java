package com.jdh.ecommerce;

import com.jdh.ecommerce.service.HammerCollectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectDtoTest {

    @Autowired
    HammerCollectService hammerCollectDao;
    @Test
    public void test() {
       /* Page<CollectDto> p = new Page<>(1, 3);
        IPage<CollectDto> demoEntityIPage = hammerCollectDao.getMyCollect(p,1);
        System.out.println(        demoEntityIPage.getRecords());*/
    }
}
