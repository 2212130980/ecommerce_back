package com.jdh.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdh.ecommerce.bean.CartDto;
import com.jdh.ecommerce.bean.OrderDto;
import com.jdh.ecommerce.entity.HammerOrderDetailEntity;
import com.jdh.ecommerce.entity.HammerOrderEntity;
import com.jdh.ecommerce.entity.HammerProductEntity;
import com.jdh.ecommerce.entity.Result;
import com.jdh.ecommerce.service.HammerCartsService;
import com.jdh.ecommerce.service.HammerOrderDetailService;
import com.jdh.ecommerce.service.HammerOrderService;
import com.jdh.ecommerce.service.HammerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("hammerorder")
public class HammerOrderController {
    @Autowired
    private HammerOrderService hammerOrderService;
    @Autowired
    private HammerOrderDetailService hammerOrderDetailService;
    @Autowired
    private HammerCartsService hammerCartsService;
    @Autowired
    private HammerProductService hammerProductService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       Integer userId) {
        Page<HammerOrderEntity> p = new Page<>(page, limit);
        IPage<OrderDto> orderDtoIPage = hammerOrderService.selOrderDto(p, userId);
        return Result.ok().put("page", orderDtoIPage);
    }

    /**
     * 列表
     */
    @RequestMapping("/getlist")
    public Result getList(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer limit,
                          String  orderNumber,String logisticsStatus,String userName) {
        Page<HammerOrderEntity> p = new Page<>(page, limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (orderNumber != null && !"null".equals(orderNumber) && !"".equals(orderNumber)) {
            queryWrapper.like("order_number",orderNumber);
        }
        if (logisticsStatus != null && !"null".equals(logisticsStatus) && !"".equals(logisticsStatus)) {
            queryWrapper.eq("logistics_status",logisticsStatus);
        }
        if (userName != null && !"null".equals(userName) && !"".equals(userName)) {
            queryWrapper.eq("user_name",userName);
        }
        queryWrapper.orderByDesc("create_time");
        IPage<HammerOrderEntity> orderDtoIPage = hammerOrderService.getAllRoles(p, queryWrapper);
        return Result.ok().put("page", orderDtoIPage);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public Result info(String orderNumber) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (orderNumber != null) {
            queryWrapper.eq("order_number", orderNumber);
            HammerOrderEntity one = hammerOrderService.getOne(queryWrapper);
            return Result.ok().put("hammerOrder", one);
        }
        return Result.error(1, "订单号为空");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @Transactional
    public Result save(HammerOrderEntity hammerOrder, @RequestBody List<CartDto> list) {
        synchronized (this) {
            hammerOrder.setId(null);
            hammerOrderService.addOrder(hammerOrder);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("order_number", hammerOrder.getOrderNumber());
            HammerOrderEntity one = hammerOrderService.getOne(queryWrapper);
            List<HammerOrderDetailEntity> cartList = new ArrayList<>();
            for (CartDto car : list) {
                HammerOrderDetailEntity hammerOrderDetailEntity = new HammerOrderDetailEntity();
                Integer price = null;
                Integer number = null;
                System.out.println(car.getHammerProductEntities());
                for (HammerProductEntity cartsEntity1 : car.getHammerProductEntities()) {
                    System.out.println((cartsEntity1.getId().equals(car.getProductId()))+"-----"+cartsEntity1.getId() +"---"+car.getProductId());
                    if (cartsEntity1.getId().equals(car.getProductId())) {
                        price = cartsEntity1.getPrice();
//                        剩余数量
                        number = Integer.valueOf(cartsEntity1.getProductRepertory());
                    }
                }
//            BeanUtils.copyProperties(car.getHammerProductEntities(), cartsEntity);
                hammerOrderDetailEntity.setOrderId(one.getId());
                hammerOrderDetailEntity.setProductId(car.getProductId());
//          要购买的数量
                hammerOrderDetailEntity.setQuantity(car.getCartNum());
                hammerOrderDetailEntity.setCost(price);
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("id", car.getProductId());
                HammerProductEntity one1 = hammerProductService.getOne(queryWrapper1);

//            库存数量大于要购买的数量
                if (Integer.valueOf(one1.getProductRepertory()) - number < 0) {
                    return Result.error(1, "当前商品库存不足");
                }
                hammerProductService.updateProductRepertory(car.getCartNum(), car.getProductId());
                cartList.add(hammerOrderDetailEntity);
                hammerCartsService.removeById(car.getId());
            }
            hammerOrderDetailService.saveBatch(cartList);

            return Result.ok().put("order", one);
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(HammerOrderEntity hammerOrder) {
        hammerOrderService.updateById(hammerOrder);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/updatestatus")
    public Result updateStatus(HammerOrderEntity hammerOrder) {
        hammerOrderService.updateById(hammerOrder);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(Integer[] ids) {
        boolean b = hammerOrderService.removeByIds(Arrays.asList(ids));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("order_id", ids);
        boolean remove = hammerOrderDetailService.remove(queryWrapper);
        if (b && remove) {
            return Result.ok();
        }
        return Result.error(1, "删除失败");
    }

}
