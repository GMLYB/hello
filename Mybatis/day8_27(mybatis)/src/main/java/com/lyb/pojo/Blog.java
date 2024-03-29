package com.lyb.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Blog implements Serializable {
    private String id;
    private String title;
    private String author;
    private Date createTime;//与数据库不一致
    private int views;
}
