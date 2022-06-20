package com.example.demo.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.util.ResultObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

public abstract class BaseController<T> {

    public abstract IService<T> getService();

    public abstract QueryWrapper<T> getQueryWrapper(T entity);

    @RequestMapping("/page")
    public ResultObject page(Page<T> page,T entity) {
        QueryWrapper<T> wrapper = this.getQueryWrapper(entity);
        return ResultObject.success(this.getService().page(page, wrapper));
    }

    @RequestMapping("/getById")
    public ResultObject getById(String id) {
        return ResultObject.success(this.getService().getById(id));
    }

    @RequestMapping("/saveOrUpdate")
    public ResultObject saveOrUpdate(@RequestBody T entity) {
        return ResultObject.success(this.getService().saveOrUpdate(entity));
    }

    @RequestMapping("/remove")
    public ResultObject remove(@RequestBody String[] ids) {
        return ResultObject.success(this.getService().removeByIds(Arrays.asList(ids)));
    }

}
