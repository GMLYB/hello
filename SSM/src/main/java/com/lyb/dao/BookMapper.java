package com.lyb.dao;

import com.lyb.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    List<Book> queryBooks();

    int addBook(Book book);

    Book queryBookById(@Param("bid") int id);

    int updateBook(Book book);

    int deleteBook(@Param("bid") int id);
}
