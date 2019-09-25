package com.jdh.ecommerce.service.impl;

import com.jdh.ecommerce.service.TokenService;
import com.jdh.ecommerce.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Resource
    private RedisUtil redisUtil;

    //生成token(格式为token:设备-加密的用户名-时间-六位随机数)
    public String generateToken(Long userId) {
        StringBuilder token = new StringBuilder();
        //加密的用户名
        token.append(userId);
        //时间
        token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "-");
        //六位随机字符串
        token.append(new Random().nextInt(9999 - 1111 + 1) + 1111);
//        System.out.println("token-->" + token.toString());
        return token.toString();
    }

    String token = null;

    //把token存到redis中
    public String save(String key) {
        if (redisUtil.get(key) == null) {
            token = generateToken(Long.valueOf(key));
            redisUtil.set(key, token, 60*120);
        }
        return token;
    }

    @Override
    public void del(String key) {
        redisUtil.del(key);
        System.out.println("删除成功");
    }

    @Override
    public void set(String key, String value) {
         redisUtil.set(key,value,60000);
    }
    @Override
    public void setObj(String key, Object value) {
        redisUtil.setObj(key,value);
    }

    @Override
    public Object get(String key) {
        return redisUtil.get(key);
    }

    @Override
    public Object getObj(String key) {
        return redisUtil.getObj(key);
    }

    @Override
    public void saveObj(String key, Object value) {
        redisUtil.setObj(key,value);
    }


    /*//把token存到redis中
    public void save(String token, HammerUserEntity user) {
        if (token.startsWith("token:PC")) {
            redisUtil.setex(token, JSONObject.toJSONString(user), 2 * 60 * 60);
        } else {
            redisUtil.set(token, JSONObject.toJSONString(user));
        }
    }
*/
}