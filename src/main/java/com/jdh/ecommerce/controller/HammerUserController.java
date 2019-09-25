package com.jdh.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.jdh.ecommerce.bean.Dto;
import com.jdh.ecommerce.entity.HammerUserEntity;
import com.jdh.ecommerce.entity.Result;
import com.jdh.ecommerce.service.HammerUserService;
import com.jdh.ecommerce.service.TokenService;
import com.jdh.ecommerce.utils.MD5Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;


@RestController
@RequestMapping("hammeruser")
public class HammerUserController {
    @Autowired
    private HammerUserService hammerUserService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DefaultKaptcha kaptcha;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "3") Integer limit,
                       String key) {
        Page<HammerUserEntity> p = new Page<>(page, limit);
        IPage<HammerUserEntity> orderDtoIPage = hammerUserService.getAllRoles(p, new QueryWrapper());
        return Result.ok().put("page", orderDtoIPage);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public Result info(@RequestBody HammerUserEntity hammerUserEntity, String username, String password) {
        System.out.println(hammerUserEntity);
        Dto dto = new Dto();
        QueryWrapper<HammerUserEntity> hammerUserEntityQueryWrapper = new QueryWrapper<>();
        if (!hammerUserEntity.getUserName().isEmpty()) {
            hammerUserEntityQueryWrapper.eq("user_name", hammerUserEntity.getUserName());
        }
        if (!hammerUserEntity.getUserPassword().isEmpty()) {
            String md5String = MD5Tools.string2MD5(hammerUserEntity.getUserPassword());
            hammerUserEntityQueryWrapper.eq("user_password", md5String);
        }
        HammerUserEntity hammerUser = hammerUserService.getOne(hammerUserEntityQueryWrapper);
        if (hammerUser == null) {
            return Result.error(1, "登陆失败，用户名或密码错误");
        }
        if (hammerUser.getIsDelete() == 1) {
            return Result.error(1, "您的账户已被禁用");
        }
        String token = tokenService.save(hammerUser.getId().toString());
        tokenService.saveObj(token, hammerUser);
        dto.setIsLogin("true");
        dto.setToken(token);
        dto.setTokenCreatedDate(System.currentTimeMillis());
        dto.setTokenExpiryDate(System.currentTimeMillis() + 2 * 60 * 60 * 1000);
        return Result.ok().put("hammerUser", hammerUser).put("dto", dto);
    }

    @RequestMapping("/selById")
    public Result selById(Integer id) {
        System.out.println(id + "--------");
        QueryWrapper<HammerUserEntity> hammerUserEntityQueryWrapper = new QueryWrapper<>();
        if (id == null) {
            return Result.error(3, "没有获取到当前用户的id");
        }
        hammerUserEntityQueryWrapper.eq("id", id);
        HammerUserEntity hammerUser = hammerUserService.getOne(hammerUserEntityQueryWrapper);

        return Result.ok().put("hammerUser", hammerUser);
    }

    @RequestMapping("loginout")
    public Result loginOut(String id, String token) {
        tokenService.del(token);
        tokenService.del(id);
        return Result.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(HammerUserEntity hammerUser, String code) {
        hammerUser.setUserPassword(MD5Tools.string2MD5(hammerUser.getUserPassword()));
        if (!code.equals(tokenService.get("kaptchaText"))) {
            return Result.error(2, "验证码错误，注册失败");
        }
        boolean register = hammerUserService.register(hammerUser);
        if (register) {
            return Result.ok();
        }
        return Result.error("1");
    }

    /**
     * 保存
     */
    @RequestMapping("/saveadmin")
    public Result saveAdmin(HammerUserEntity hammerUser) {
        hammerUser.setType(2);
        boolean register = hammerUserService.save(hammerUser);
        if (register) {
            return Result.ok();
        }
        return Result.error("1");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(HammerUserEntity hammerUser) {
        if (hammerUser.getUserPassword() != null) {
            hammerUser.setUserPassword(MD5Tools.string2MD5(hammerUser.getUserPassword()));
        }
        hammerUserService.updateById(hammerUser);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/updatestatus")
    public Result updateStatus(HammerUserEntity hammerUser) {
        hammerUserService.updateById(hammerUser);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", hammerUser.getId());
        HammerUserEntity one = hammerUserService.getOne(queryWrapper);
        String token = tokenService.get(hammerUser.getId().toString()).toString();
        tokenService.setObj(token, one);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(Integer[] ids) {
        hammerUserService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }


    @GetMapping("/kaptcha")
    public void kaptcha(HttpServletResponse servletResponse) {
        byte[] kaptchaBytes = null;
        ByteArrayOutputStream byteArrOUT = new ByteArrayOutputStream();
        String kaptchaText = kaptcha.createText();
        tokenService.set("kaptchaText", kaptchaText);
//        System.out.println(kaptchaText);
        BufferedImage bufferedImage = kaptcha.createImage(kaptchaText);
        try {
            ImageIO.write(bufferedImage, "jpg", byteArrOUT);
        } catch (IOException e) {
            return;
        }
        //定义输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        kaptchaBytes = byteArrOUT.toByteArray();
        servletResponse.setHeader("Cache-Control", "no-store");
        servletResponse.setHeader("Pragma", "no-cache");
        servletResponse.setDateHeader("Expires", 0);
        servletResponse.setContentType("image/jpeg");
        try {
            ServletOutputStream servletOutputStream = servletResponse.getOutputStream();
            servletOutputStream.write(kaptchaBytes);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
