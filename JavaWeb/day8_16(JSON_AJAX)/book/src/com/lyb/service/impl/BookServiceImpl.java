package com.lyb.service.impl;

import com.lyb.dao.BookDao;
import com.lyb.dao.impl.BookDaoImpl;
import com.lyb.pojo.Book;
import com.lyb.pojo.Page;
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

    @Override
    public Page<Book> page(int pageNow, int pageSize) {
        Page<Book> page = new Page<Book>();
        //1. 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求 总 记 录 数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //2. 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //3. 设置总页码
        page.setPageTotal(pageTotal);
        //4. 设置当前页码
        page.setPageNow(pageNow);
        //求当前页数据的开始索引
        int begin = (page.getPageNow() - 1) * pageSize;
        // 5. 求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNow, int pageSize, int min, int max) {

        Page<Book> page = new Page<Book>();
        //1. 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求 总 记 录 数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //2. 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //3. 设置总页码
        page.setPageTotal(pageTotal);
        //4. 设置当前页码
        page.setPageNow(pageNow);
        //求当前页数据的开始索引
        int begin = (page.getPageNow() - 1) * pageSize;
        // 5. 求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
