package com.lyb.springboot06.compoment;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


//@Component("localeResolver")
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取参数
        String l = httpServletRequest.getParameter("l");
        //创建 locale 并设置默认值（默认值为浏览器设置）
        Locale locale = Locale.getDefault();
        //判断 参数 l 的值是否为空
        if(!StringUtils.isEmpty(l)){
            //zh_CN 需要分割放到 locale 中
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
