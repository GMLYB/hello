package com.lyb.service;

import com.lyb.pojo.Book;
import com.lyb.pojo.Page;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBookById(Integer id);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(int pageNow, int pageSize);

    Page<Book> pageByPrice(int pageNow, int pageSize, int min, int max);
}
