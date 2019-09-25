package com.jdh.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdh.ecommerce.entity.HammerUserAddressEntity;
import com.jdh.ecommerce.entity.Result;
import com.jdh.ecommerce.service.HammerUserAddressService;
import com.jdh.ecommerce.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("hammeruseraddress")
public class HammerUserAddressController {
    @Autowired
    private HammerUserAddressService hammerUserAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer limit,
                       Integer userId) {
        Page<HammerUserAddressEntity> p = new Page<>(page, limit);
        QueryWrapper<HammerUserAddressEntity> queryWrapper = new QueryWrapper();
        if (userId == null) {
            return Result.error(1, "userId为null");
        }
        queryWrapper.eq("user_id", userId);
        IPage<HammerUserAddressEntity> demoEntityIPage = hammerUserAddressService.getAllRoles(p, queryWrapper);
        PageUtils pageUtils = new PageUtils(demoEntityIPage.getRecords(), demoEntityIPage.getTotal(), demoEntityIPage.getSize(), demoEntityIPage.getCurrent());
        System.out.println(pageUtils);
        return Result.ok().put("page", pageUtils);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id) {
        HammerUserAddressEntity hammerUserAddress = hammerUserAddressService.getById(id);

        return Result.ok().put("hammerUserAddress", hammerUserAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(HammerUserAddressEntity hammerUserAddress) {
//        String consignee = hammerUserAddress.getConsignee();
        if ("1".equals(hammerUserAddress.getChecked())) {
            hammerUserAddressService.updAddress(hammerUserAddress.getUserId());
        }
        if (hammerUserAddress.getRemake() == null || hammerUserAddress.getRemake().equals("")) {
            hammerUserAddress.setRemake("无");
        }
        hammerUserAddressService.addAddress(hammerUserAddress);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody HammerUserAddressEntity hammerUserAddress) {
        hammerUserAddressService.updateById(hammerUserAddress);
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(Integer[] ids) {
        hammerUserAddressService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
