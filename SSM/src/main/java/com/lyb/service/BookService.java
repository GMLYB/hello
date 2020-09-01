package com.lyb.service;

import com.lyb.pojo.Book;

import java.util.List;

public interface BookService {

    List<Book> queryBooks();

    int addBook(Book book);

    Book queryBookById(int id);

    int updateBook(Book book);

    int deleteBook(int id);
}
