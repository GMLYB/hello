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

    @Test
    public void queryForPageTotalCount() {
        Integer count = bookDao.queryForPageTotalCount();
        System.out.println(count);
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(0, 4);
        books.forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCountByPrice( ) {
        Integer countByPrice = bookDao.queryForPageTotalCountByPrice(10, 50);
        System.out.println(countByPrice);
    }

    @Test
    public void queryForPageItemsByPrice( ) {
        List<Book> books = bookDao.queryForPageItemsByPrice(0, 50, 10, 50);
        books.forEach(System.out::println);
    }

}