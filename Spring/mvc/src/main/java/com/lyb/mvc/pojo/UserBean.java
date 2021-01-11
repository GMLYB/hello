package com.lyb.mvc.pojo;


import com.lyb.mvc.anno.Enumration;


import javax.validation.constraints.*;


public class UserBean {
    private Integer uid;

    @NotBlank
    private String uname;

    @Enumration(options = {"男","女"})
    private String sex;

//    @Min(value = 1)
//    @Max(value = 100)
    @Size(min = 1,max = 100)
    @NotNull
    private Integer age;

    @NotBlank
    @Enumration(value = "sf")
    private String address;

    public UserBean() {
    }

    public UserBean(Integer uid, String uname, Integer age, String sex, String address) {
        this.uid = uid;
        this.uname = uname;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

