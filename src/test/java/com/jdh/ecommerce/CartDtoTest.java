package com.jdh.ecommerce;

import com.jdh.ecommerce.service.HammerCartsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartDtoTest {

    @Autowired
    HammerCartsService hammerCartsService;

    @Test
    public void test() {
       /* Page<CartDto> p = new Page<>(1, 3);
        IPage<CartDto> demoEntityIPage = hammerCartsService.getAllRoles(p,1,new Integer[]{85,79});
        System.out.println(        demoEntityIPage.getRecords());*/
    }


    @Test
    public void test1() {
        /*List<HammerCartsEntity> cartDtos= new ArrayList<>();
        HammerCartsEntity hammerCartsEntity1 = new HammerCartsEntity();
        hammerCartsEntity1.setId(79l);
        hammerCartsEntity1.setCartNum(2);
        cartDtos.add(hammerCartsEntity1);
        HammerCartsEntity hammerCartsEntity2 = new HammerCartsEntity();
        hammerCartsEntity2.setId(82l);
        hammerCartsEntity2.setCartNum(3);
        cartDtos.add(hammerCartsEntity2);
        hammerCartsService.updateBatchById(cartDtos);
        System.out.println(1);*/
    }



}
