package com.lyb.service.impl;

import com.lyb.dao.BookDao;
import com.lyb.dao.impl.BookDaoImpl;
import com.lyb.pojo.Book;
import com.lyb.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public Book queryBookById(Integer id) {
        Book book = bookDao.queryBookById(id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        List<Book> list = bookDao.queryBooks();
        return list;
    }
}
