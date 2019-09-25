package com.jdh.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdh.ecommerce.bean.CartDto;
import com.jdh.ecommerce.entity.HammerCartsEntity;
import com.jdh.ecommerce.entity.HammerProductEntity;
import com.jdh.ecommerce.entity.Result;
import com.jdh.ecommerce.service.HammerCartsService;
import com.jdh.ecommerce.service.HammerProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("hammercarts")
public class HammerCartsController {
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
                       Integer id, Integer[] cartId) {
        Page<CartDto> p = new Page<>(page, limit);
        IPage<CartDto> cartDtoIPage = hammerCartsService.getAllRoles(p, id, cartId);
        System.out.println(cartDtoIPage.getRecords());
        return Result.ok().put("page", cartDtoIPage);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public Result info(Integer id) {
        HammerCartsEntity hammerCarts = hammerCartsService.getById(id);
        return Result.ok().put("hammerCarts", hammerCarts);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(HammerCartsEntity hammerCarts) {
        if (hammerCarts == null) {
            return Result.error(1, "传入对象为空");
        }
        QueryWrapper<HammerCartsEntity> hammerCartsEntityQueryWrapper = new QueryWrapper<>();
        hammerCartsEntityQueryWrapper.eq("user_id", hammerCarts.getUserId());
        hammerCartsEntityQueryWrapper.eq("product_id", hammerCarts.getProductId());
        HammerCartsEntity one = hammerCartsService.getOne(hammerCartsEntityQueryWrapper);
        HammerProductEntity byId = hammerProductService.getById(hammerCarts.getProductId());
        if (one == null) {
            hammerCarts.setId(null);
            hammerCartsService.addMyCart(hammerCarts);
            return Result.ok();
        }
        one.setCartNum(one.getCartNum() + 1);
        if (Integer.valueOf(byId.getProductRepertory()) < one.getCartNum()){
            return Result.error(1,"商品库存不足");
        }
        boolean b = hammerCartsService.updateById(one);
        if (b) {
            return Result.ok();
        } else {
            return Result.error(1, "修改数量失败");
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    public Result update(Integer[] ids, @RequestBody List<CartDto> hammerCarts) {
        List<HammerCartsEntity> cartList = new ArrayList<>();
        for (CartDto car : hammerCarts) {
            HammerCartsEntity cartsEntity = new HammerCartsEntity();
            BeanUtils.copyProperties(car, cartsEntity);
            for (int i =0;i<ids.length;i++){
                if (car.getId().equals(ids[i].longValue())) {
                    cartList.add(cartsEntity);
                }
            }
        }
        hammerCartsService.updateBatchById(cartList);
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(Integer[] ids) {
        boolean b = hammerCartsService.removeByIds(Arrays.asList(ids));
        if (b) {
            return Result.ok();
        }
        return Result.error(1,"删除失败");
    }

}
