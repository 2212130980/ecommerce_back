package com.jdh.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdh.ecommerce.bean.CollectDto;
import com.jdh.ecommerce.entity.HammerCollectEntity;
import com.jdh.ecommerce.entity.Result;
import com.jdh.ecommerce.service.HammerCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("hammercollect")
public class HammerCollectController {
    @Autowired
    private HammerCollectService hammerCollectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "3") Integer limit,
                       Integer id) {
        Page<CollectDto> p = new Page<>(page, limit);
        IPage<CollectDto> demoEntityIPage = hammerCollectService.getMyCollect(p, id);
        System.out.println(demoEntityIPage.getRecords());
        return Result.ok().put("page", demoEntityIPage);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id) {
        HammerCollectEntity hammerCollect = hammerCollectService.getById(id);
        return Result.ok().put("hammerCollect", hammerCollect);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(HammerCollectEntity hammerCollect) {

        if (hammerCollect == null) {
            return Result.error(1, "传入对象为空");
        }
        QueryWrapper<HammerCollectEntity> hammerCartsEntityQueryWrapper = new QueryWrapper<>();
        hammerCartsEntityQueryWrapper.eq("user_id", hammerCollect.getUserId());
        hammerCartsEntityQueryWrapper.eq("product_id", hammerCollect.getProductId());
        HammerCollectEntity one = hammerCollectService.getOne(hammerCartsEntityQueryWrapper);
        if (one == null) {
            hammerCollect.setId(null);
            hammerCollectService.addMyCollect(hammerCollect);
            return Result.ok();
        }
        return Result.error(1, "您已收藏过改商品");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody HammerCollectEntity hammerCollect) {
        hammerCollectService.updateById(hammerCollect);
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(Integer[] ids) {
        hammerCollectService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
