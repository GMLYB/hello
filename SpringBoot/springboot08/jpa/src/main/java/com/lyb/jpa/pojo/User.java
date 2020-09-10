package com.lyb.jpa.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity//告诉JPA这是一个实体类（和数据表的映射的类）
@Table(name = "tb1_user")//@Table 来指定和哪一个数据表对应，如果省略，默认表名为user
public class User {

    @Id//这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    private Integer id;

    @Column(name = "last_name",length = 50)//这是和数据表对应的一个列
    private String lastName;
    @Column//省略 默认列名就是属性名
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
