package com.lyb.test;

import com.lyb.pojo.Book;
import com.lyb.service.BookService;
import com.lyb.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    BookService service = new BookServiceImpl();

    @Test
    public void addBook() {
        service.addBook(new Book(1,"新书一本","GoodMan",new BigDecimal(999),20,10,null));
    }

    @Test
    public void updateBook() {
        service.updateBook(new Book(25,"烂book一本","GoodMan",new BigDecimal(999),20,10,null));
    }

    @Test
    public void deleteBookById() {
        service.deleteBookById(25);
    }

    @Test
    public void queryBookById() {
        Book book = service.queryBookById(25);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> list = service.queryBooks();
        list.forEach(System.out::println);
    }
}