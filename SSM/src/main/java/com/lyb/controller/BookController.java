package com.lyb.controller;

import com.lyb.pojo.Book;
import com.lyb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;


    @RequestMapping("/all")
    public String queryBooks(Model model){
        List<Book> books = bookService.queryBooks();
        model.addAttribute("books",books);
        return "book/show";
    }

    @GetMapping("/add")
    public String ToAddBook(){
        return "book/update";
    }

    @PostMapping("/add")
    public String addBook(Book book){
        bookService.addBook(book);
        return "redirect:/book/all";
    }

    @GetMapping("/update")
    public String ToUpdateBook(@RequestParam("id") int id, Model model){
        Book book = bookService.queryBookById(id);
        model.addAttribute("book",book);
        return "book/update";
    }

    @PostMapping("/update")
    public String updateBook(Book book){
        bookService.updateBook(book);
        return "redirect:/book/all";
    }

    @RequestMapping("/delete")
    public String deleteBook(@RequestParam("id")int id){
        bookService.deleteBook(id);
        return "redirect:/book/all";
    }

}
