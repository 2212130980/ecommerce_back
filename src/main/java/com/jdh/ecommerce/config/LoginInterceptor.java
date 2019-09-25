package com.jdh.ecommerce.config;

import cn.hutool.json.JSONUtil;
import com.jdh.ecommerce.entity.HammerUserEntity;
import com.jdh.ecommerce.entity.Result;
import com.jdh.ecommerce.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //告诉浏览器允许所有的域访问
//注意 * 不能满足带有cookie的访问,Origin 必须是全匹配
//resp.addHeader("Access-Control-Allow-Origin", "*");
//解决办法通过获取Origin请求头来动态设置
        String origin = request.getHeader("Origin");
//        http://www.smartisanaback.com
        if (("http://www.smartisanaback.com").equals(origin)) {
            return true;
        }
        if (("http://localhost:8090").equals(origin)) {
            return true;
        }
        if (StringUtils.hasText(origin)) {
            response.addHeader("Access-Control-Allow-Origin", origin);
        }
//允许带有cookie访问
        response.addHeader("Access-Control-Allow-Credentials", "true");
//告诉浏览器允许跨域访问的方法
        response.addHeader("Access-Control-Allow-Methods", "*");
//告诉浏览器允许带有Content-Type,header1,header2头的请求访问
//resp.addHeader("Access-Control-Allow-Headers", "Content-Type,header1,header2");
//设置支持所有的自定义请求头
        String headers = request.getHeader("Access-Control-Request-Headers");
        if (StringUtils.hasText(headers)) {
            response.addHeader("Access-Control-Allow-Headers", headers);
        }
//告诉浏览器缓存OPTIONS预检请求1小时,避免非简单请求每次发送预检请求,提升性能
        response.addHeader("Access-Control-Max-Age", "3600");
        String token = request.getHeader("Authentication-Token");
        String id = request.getHeader("Authentication-userId");
//      String userId= request.getHeader("userId");
//        System.out.println(token+"---"+tokenService.get(userId));
//      if (token.equals(tokenService.get(userId))) {
//          response.getWriter().print("1");
//      }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        System.out.println(id + "*******" + token);
        if (id == null || token == null) {
            response.getWriter().println(JSONUtil.parse(Result.error(401, "没有登陆")));
            response.getWriter().flush();
            return false;
        }
        if (token.equals(tokenService.get(id))) {
            HammerUserEntity o = (HammerUserEntity) tokenService.getObj(token);
            Integer isDelete = o.getIsDelete();
            if (isDelete == 1) {
                response.getWriter().println(JSONUtil.parse(Result.error(402, "您的账户被禁用")));
                response.getWriter().flush();
                return false;
            }
            return true;
        }
        response.getWriter().println(JSONUtil.parse(Result.error(403, "您的登陆有效期到了")));
        response.getWriter().flush();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}