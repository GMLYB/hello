package com.lyb.test;

public class StaticProxy {
    public static void main(String[] args) {
        Star star = new Star();
        StarProxy proxy = new StarProxy(star);

        proxy.sing();

    }
}

interface sing{
    void sing();
}


class Star implements sing{

    @Override
    public void sing() {
        System.out.println("明星唱歌");
    }
}

class StarProxy implements sing{

    private Star star;

    public StarProxy(Star star) {
        this.star = star;
    }

    @Override
    public void sing() {
        System.out.println("明星唱歌准备工作");

        star.sing();

        System.out.println("明星唱歌收尾工作");

    }
}