package com.lyb.mvc.dao;

import com.lyb.mvc.pojo.UserBean;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDao {

    private static Map<Integer, UserBean> userMap = new HashMap<>();

    public boolean addUser(UserBean user) {
        userMap.put(user.getUid(), user);
        System.out.println("添加用户成功, username:" + user.getUname());
        return true;
    }

    public static List<String> getEnumList(String enumName){
        //如果传入的是 sf 就返回 省份的List
        if ("sf".equalsIgnoreCase(enumName)){
            return getSFList();
        }
        //都不符合就返回空的list
        return Collections.EMPTY_LIST;
    }

    public static List<String> getSFList(){
        //按道理是从数据库获取的
        List<String> SFlist = new ArrayList<>();
        SFlist.add("北京");
        SFlist.add("上海");
        SFlist.add("深圳");
        SFlist.add("广州");
        SFlist.add("广西");
        SFlist.add("新疆");
        SFlist.add("西藏");
        SFlist.add("海南");
        return SFlist;
    }


}