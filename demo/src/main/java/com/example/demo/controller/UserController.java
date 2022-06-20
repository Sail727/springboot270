package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.base.controller.BaseController;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.ResultObject;
import com.example.demo.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Scope(value = "prototype")
public class UserController extends BaseController<User> {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (user != null) {
            if (StringUtils.isNoneBlank(user.getUsername())) wrapper.like("username", user.getUsername());
            if (StringUtils.isNoneBlank(user.getRealName())) wrapper.like("realName", user.getRealName());
            if (StringUtils.isNoneBlank(user.getMobile())) wrapper.like("mobile", user.getMobile());
            if (StringUtils.isNoneBlank(user.getEmail())) wrapper.like("email", user.getEmail());
        }

        return wrapper;
    }

    @Override
    public IService<User> getService() {
        return userService;
    }


    @RequestMapping("/login")
    public Map login(String username, String password) {
        HashMap<String, Object> map = new HashMap<>();

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", username);
        wrapper.eq("passWord", password);
        User user = userService.getOne(wrapper);
        if (user != null) {
            String token = TokenUtil.sign(user.getUsername(), user.getId());
            map.put("code", 0);
            map.put("access_token", token);
        } else {
            map.put("code", 1);
            map.put("msg", "用户名密码错误！");
        }
        return map;
    }

    @RequestMapping("/logout")
    public ResultObject logout() {
        return ResultObject.success();
    }

    @RequestMapping("/getUser")
    public ResultObject getUser() {
        User user = userService.getById("-1");
        return ResultObject.success(user);
    }

    @RequestMapping("/getMenu")
    public ResultObject getMenu() {
        String jsonStr = JsonUtil.readJsonFile("json/menu.json");
        JSONArray data = JSONObject.parseArray(jsonStr);
        return ResultObject.success(data);
    }


}
