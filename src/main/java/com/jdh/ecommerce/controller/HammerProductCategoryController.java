package com.jdh.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdh.ecommerce.entity.HammerProductCategoryEntity;
import com.jdh.ecommerce.entity.Result;
import com.jdh.ecommerce.service.HammerProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("hammerproductcategory")
public class HammerProductCategoryController {
    @Autowired
    private HammerProductCategoryService hammerProductCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "100") Integer limit,
                       Integer type,Integer parentId) {
        Page<HammerProductCategoryEntity> p = new Page<>(page, limit);
        QueryWrapper<HammerProductCategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type);
        if (parentId!=null){
            queryWrapper.eq("parent_id",parentId);
        }
        IPage<HammerProductCategoryEntity> demoEntityIPage =
                hammerProductCategoryService.getAllRoles(p,queryWrapper);
        return Result.ok().put("page", demoEntityIPage);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id) {
        HammerProductCategoryEntity hammerProductCategory = hammerProductCategoryService.getById(id);

        return Result.ok().put("hammerProductCategory", hammerProductCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody HammerProductCategoryEntity hammerProductCategory) {
        hammerProductCategoryService.save(hammerProductCategory);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody HammerProductCategoryEntity hammerProductCategory) {
        hammerProductCategoryService.updateById(hammerProductCategory);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] ids) {
        hammerProductCategoryService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
