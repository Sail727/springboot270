package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "creatorId", String.class, "-1");
        this.strictInsertFill(metaObject, "creator", String.class, "管理员");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updaterId", String.class, "-1");
        this.strictUpdateFill(metaObject, "updater", String.class, "管理员");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
