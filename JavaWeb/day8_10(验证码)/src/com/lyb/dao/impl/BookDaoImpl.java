package com.lyb.dao.impl;

import com.lyb.dao.BaseDao;
import com.lyb.dao.BookDao;
import com.lyb.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`id`, `name`, `author`, `price`, `sales`, `stock`, `img_path`) " +
                "VALUES(NULL , ? ,?, ? , ? , ? , ?)";
        int i = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return i;
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        int i = update(sql, id);
        return i;
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name` = ?,`author` = ?, `price` = ?, `sales` = ?, `stock` = ?, `img_path` = ? where id = ?";
        int i = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        return i;
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book where id = ?";
        Book book = queryForOne(Book.class, sql, id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book";
        List<Book> books = queryForList(Book.class, sql);
        return books;
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        List<Book> books = queryForList(Book.class, sql, begin, pageSize);
        return books;
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book " +
                "where price between ? and ? order by price  limit ?,?";
        List<Book> books = queryForList(Book.class, sql, min, max, begin, pageSize);
        return books;
    }


}
