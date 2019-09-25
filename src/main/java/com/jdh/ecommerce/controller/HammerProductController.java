package com.jdh.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdh.ecommerce.entity.HammerProductEntity;
import com.jdh.ecommerce.entity.Result;
import com.jdh.ecommerce.service.HammerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


@RestController
@RequestMapping("hammerproduct")
public class HammerProductController {
    @Autowired
    private HammerProductService hammerProductService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "8") Integer limit,
                       Integer categoryLevel1, Integer categoryLevel2,
                       String price, String productName,
                       String productRepertory,String id) {
        QueryWrapper<HammerProductEntity> queryWrapper = new QueryWrapper();
        if (categoryLevel1 != null) {
            queryWrapper.eq("category_level1", categoryLevel1);
        }
        if (categoryLevel2 != null) {
            queryWrapper.eq("category_level2", categoryLevel2);
        }
        if ("1".equals(price)) {
            queryWrapper.orderByAsc("price");
        }
        if ("2".equals(price)) {
            queryWrapper.orderByDesc("price");
        }
        if (productName != null && !"".equals(productName)&&!"null".equals(productName)) {
            queryWrapper.like("product_name", productName);
        }
        if ("1".equals(productRepertory)) {
            queryWrapper.orderByAsc("product_repertory");
        }
        if (id != null && !"null".equals(id) && !"".equals(id)) {
            queryWrapper.eq("id",id);
        }
        Page<HammerProductEntity> p = new Page<>(page, limit);
        IPage<HammerProductEntity> demoEntityIPage = hammerProductService.getAllRoles(p, queryWrapper);
        return Result.ok().put("page", demoEntityIPage);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public Result info(Integer id) {
        HammerProductEntity hammerProduct = hammerProductService.getById(id);

        return Result.ok().put("hammerProduct", hammerProduct);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(HammerProductEntity hammerProduct) {
        hammerProductService.save(hammerProduct);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(HammerProductEntity hammerProduct) {
        hammerProductService.updateById(hammerProduct);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(Integer[] ids) {
        boolean b = hammerProductService.removeByIds(Arrays.asList(ids));
        if (b) {
            return Result.ok();
        }
        return Result.error(1,"删除失败");
    }

    private String url;

    @RequestMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8")
    public Result uploadFile(@RequestParam("fileName") MultipartFile file, HammerProductEntity hammerProductEntity) {
//        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return Result.error(1, "上传的文件位空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");

        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
//        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");

        //加个时间戳，尽量避免文件名称重复
        String path = "/usr/local/image/" + fileName;
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
//        System.out.print("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return Result.error(2, "该图片已存在");
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件
//            System.out.print("保存文件路径"+path+"\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            url = "http://172.20.10.9:8888/image/" + fileName;//本地运行项目
            hammerProductEntity.setProductImg(url);
            boolean save = hammerProductService.save(hammerProductEntity);
            if (!save) {
                return Result.error(4, "新增失败");
            }
        } catch (IOException e) {
            return Result.error(3, "图片上传失败");
        }
        return Result.ok();
    }

}
