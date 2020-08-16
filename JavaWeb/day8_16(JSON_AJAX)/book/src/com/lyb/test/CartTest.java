package com.lyb.test;

import com.lyb.pojo.Cart;
import com.lyb.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"liyb",10,new BigDecimal(100),new BigDecimal(1000)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"liyb",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"liyb",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"liyb2",1,new BigDecimal(50),new BigDecimal(50)));
        System.out.println(cart);
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clear() {
    }

    @Test
    public void updateItem() {
    }
}