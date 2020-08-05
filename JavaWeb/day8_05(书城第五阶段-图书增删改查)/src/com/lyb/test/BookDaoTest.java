package com.lyb.test;

import com.lyb.dao.BookDao;
import com.lyb.dao.impl.BookDaoImpl;
import com.lyb.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(1,"新书一本","GoodMan",new BigDecimal(999),20,10,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(24,"烂book一本","GoodMan",new BigDecimal(999),20,10,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(24);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> list = bookDao.queryBooks();
        list.forEach(System.out::println);
    }
}