package com.company.Summative2MoritaSara.repository;

import com.company.Summative2MoritaSara.model.Publishers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publishers, Integer> {
}
