package com.jdh.ecommerce.controller;

import com.jdh.ecommerce.bean.CartDto;
import com.jdh.ecommerce.bean.OrderDetailDto;
import com.jdh.ecommerce.entity.HammerOrderDetailEntity;
import com.jdh.ecommerce.entity.HammerProductEntity;
import com.jdh.ecommerce.entity.Result;
import com.jdh.ecommerce.service.HammerOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("hammerorderdetail")
public class HammerOrderDetailController {
    @Autowired
    private HammerOrderDetailService hammerOrderDetailService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list( String orderId) {
        List<OrderDetailDto> detailDto = hammerOrderDetailService.selOrderDetail(orderId);
        return Result.ok().put("detailDto",detailDto) ;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id) {
        HammerOrderDetailEntity hammerOrderDetail = hammerOrderDetailService.getById(id);

        return Result.ok().put("hammerOrderDetail", hammerOrderDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(Integer  orderNumber, @RequestBody List<CartDto> list) {
//		hammerOrderDetailService.save(hammerOrderDetail);
        System.out.println("------------------------");
        List<HammerOrderDetailEntity> cartList = new ArrayList<>();
        for (CartDto car : list) {
            HammerOrderDetailEntity hammerOrderDetailEntity = new HammerOrderDetailEntity();
            Integer price = null;
            for (HammerProductEntity cartsEntity1 : car.getHammerProductEntities()) {
                if (cartsEntity1.getId() == car.getProductId()) {
                    price = cartsEntity1.getPrice();
                }
            }
//            BeanUtils.copyProperties(car.getHammerProductEntities(), cartsEntity);
                hammerOrderDetailEntity.setOrderId(orderNumber);
                hammerOrderDetailEntity.setProductId(car.getProductId());
                hammerOrderDetailEntity.setQuantity(car.getCartNum());
                hammerOrderDetailEntity.setCost(price);
                cartList.add(hammerOrderDetailEntity);
        }
        hammerOrderDetailService.saveBatch(cartList);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody HammerOrderDetailEntity hammerOrderDetail) {
        hammerOrderDetailService.updateById(hammerOrderDetail);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] ids) {
        hammerOrderDetailService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
