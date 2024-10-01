package com.javatechie.book.service;

import com.javatechie.book.exception.BookNotFoundException;
import com.javatechie.book.model.Book;
import com.javatechie.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book getBook(Long id){
        if(bookRepository.existsById(id)){
            return bookRepository.findById(id).get();
        }
        throw new BookNotFoundException("Book Details not present of: " +id);

    }

    public List<Book> getAll(){
        return bookRepository.findAll(Sort.by("ti"));
    }

    public List<Book> findbyName(String name){
        return bookRepository.findByTitle(name);
    }

    public String delete(Long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return "Book Deleted succcesssfully";
        }
        throw new RuntimeException("Book cannot be deleted" +id);
    }

    public Book updateBook(Long id, Book book){
        Book bookinDb = bookRepository.findById(id).get();
        book.setId(bookinDb.getId());
        return bookRepository.save(book);
    }


}
