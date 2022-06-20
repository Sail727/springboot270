package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.example.demo.util.TokenUtil;
import com.example.demo.util.ResultObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        String Authorization = request.getHeader("Authorization");
        if (Authorization != null) {
            if (Authorization.length() > 8){
                String token = Authorization.substring(7);
                if (TokenUtil.verify(token)) {

                    return true;
                }
            }
        }
        response.setContentType("application/json;charset=UTF-8;");
        response.getWriter().println(JSON.toJSONString(new ResultObject(401, "未登录！")));
        return false;
    }
}
