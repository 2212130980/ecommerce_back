package com.jdh.ecommerce;

import com.jdh.ecommerce.service.HammerProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
    @Autowired
    HammerProductService hammerProductService;

    @Test
    public void test() {
       /* long startTime = System.currentTimeMillis();   //获取开始时间

        List<HammerProductEntity> list = new ArrayList<>();
        for (int i = 100000; i < 1100000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            int x;//定义两变量
            Random ne = new Random();//实例化一个random的对象ne
            x = ne.nextInt(9999 - 1000 + 1) + 1000;//为变量赋随机值1000-9999
            HammerProductEntity hammerProductEntity =
                    new HammerProductEntity(null,"华为" + i+"c", 1, 2, 0, "static/img/19.png", "1000", x, uid + "华为新款5G");
            list.add(hammerProductEntity);
        }
        hammerProductService.saveBatch(list);
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");*/
    }

}
