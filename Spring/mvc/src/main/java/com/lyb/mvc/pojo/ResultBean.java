package com.lyb.mvc.pojo;


public class ResultBean<T> {
    private boolean success;

    private String msg;

    private T data;

    public static <T> ResultBean<T> getSuccess(T data) {
        ResultBean<T> bean = new ResultBean<T>();
        bean.setData(data);
        bean.setSuccess(true);
        bean.setMsg("success");
        return bean;
    }

    public static <T> ResultBean<T> getFaild(String msg, T data) {
        ResultBean<T> bean = new ResultBean<>();
        bean.setData(data);
        bean.setSuccess(false);
        bean.setMsg(msg);
        return bean;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public void setData(T data) {
        this.data = data;
    }

}
