package com.company.Summative2MoritaSara.repository;

import com.company.Summative2MoritaSara.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Authors, Integer> {
}
