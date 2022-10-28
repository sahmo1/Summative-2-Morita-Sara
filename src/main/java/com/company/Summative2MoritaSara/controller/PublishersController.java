package com.company.Summative2MoritaSara.controller;

import com.company.Summative2MoritaSara.model.Publishers;
import com.company.Summative2MoritaSara.repository.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublishersController {

    @Autowired
    PublisherRepo publisherRepository;

    @GetMapping
    public List<Publishers> getPublishers() {
        return publisherRepository.findAll();
    }

    @GetMapping("/publishers/{id}")
    public Publishers getPublisherById(@PathVariable int id) {
        Optional<Publishers> returnVal = publisherRepository.findById(id);
        if (!returnVal.isPresent()) {
            return null;
        } else {
            return returnVal.get();
        }
    }

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publishers addPublisher(@RequestBody Publishers publisher) {
        return publisherRepository.save(publisher);
    }

    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publishers publisher) {
        publisherRepository.save(publisher);
    }

    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id) {
        publisherRepository.deleteById(id);
    }
}
