package com.lyb.dao;

import com.lyb.pojo.Book;

import java.util.List;

public interface BookDao {

    //添加图书信息
    int addBook(Book book);
    //通过id删除图书信息
    int deleteBookById(Integer id);
    //修改图书信息
    int updateBook(Book book);
    //查询通过id图书信息
    Book queryBookById(Integer id);
    //查询全部信息
    List<Book> queryBooks();
}
