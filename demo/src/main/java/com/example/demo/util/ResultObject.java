package com.example.demo.util;

public class ResultObject {

    private Integer code;

    private String msg;

    private Object data;

    public static ResultObject success() {
        ResultObject object = new ResultObject();
        object.setCode(0);
        object.setMsg("success");
        return object;
    }

    public static ResultObject success(Object data) {
        ResultObject object = new ResultObject();
        object.setCode(0);
        object.setMsg("success");
        object.setData(data);
        return object;
    }

    public ResultObject() {
    }

    public ResultObject(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultObject(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
