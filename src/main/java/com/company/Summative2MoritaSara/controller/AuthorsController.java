package com.company.Summative2MoritaSara.controller;

import com.company.Summative2MoritaSara.model.Authors;
import com.company.Summative2MoritaSara.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class AuthorsController {
    @Autowired
    AuthorRepo authorRepo;

    @GetMapping("/author")
    public List<Authors> getAuthors(){
        return authorRepo.findAll();
    }

    @GetMapping("/authors/{id}")
    public Authors getAuthorById(@PathVariable int id){
        Optional<Authors> returnValue = authorRepo.findById(id);
        if (!returnValue.isPresent()){
            return null;
        }else{
            return returnValue.get();
        }
    }


    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Authors addAuthor(@RequestBody Authors authors){
        return authorRepo.save(authors);
    }

    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Authors names) {
        authorRepo.save(names);
    }

    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        authorRepo.deleteById(id);
    }


}
