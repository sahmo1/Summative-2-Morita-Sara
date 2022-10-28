package com.company.Summative2MoritaSara.controller;

import com.company.Summative2MoritaSara.model.Books;
import com.company.Summative2MoritaSara.repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class BooksController {
    @Autowired
    BooksRepo booksRepo;
    @GetMapping("/books")
    public List<Books> getBooks(){
        return booksRepo.findAll();
    }
    @GetMapping("/books/{id}")
    public Books getBookById(@PathVariable int id){

        Optional<Books> returnValue = booksRepo.findById(id);

        if (!returnValue.isPresent()){
            return null;
        }
        else{
            return returnValue.get();
        }
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Books addBook(@RequestBody Books book) {
        return booksRepo.save(book);
    }

    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Books book) {
        booksRepo.save(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        booksRepo.deleteById(id);
    }


}
