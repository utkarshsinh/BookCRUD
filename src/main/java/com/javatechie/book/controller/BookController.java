package com.javatechie.book.controller;

import com.javatechie.book.model.Book;
import com.javatechie.book.repository.BookRepository;
import com.javatechie.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/")
    ResponseEntity<Book> saveBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(book));
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> getBook(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBook(id));
    }

    @GetMapping("/find")
    List<Book> getAll(@RequestParam String author){
        return bookService.findbyName(author);
    }

    @GetMapping("/")
    List<Book> getAll(){
        return bookService.getAll();
    }


    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBook(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bookService.delete(id));
    }

    @PutMapping("/{id}")
    Book updateBook(@PathVariable Long id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }



}
