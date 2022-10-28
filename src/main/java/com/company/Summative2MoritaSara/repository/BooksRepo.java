package com.company.Summative2MoritaSara.repository;

import com.company.Summative2MoritaSara.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepo extends JpaRepository<Books, Integer> {
    Optional<Books> findByAuthorID(Integer authorID);
}
