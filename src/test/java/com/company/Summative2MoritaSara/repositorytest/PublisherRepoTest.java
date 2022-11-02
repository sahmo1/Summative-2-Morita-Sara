package com.company.Summative2MoritaSara.repositorytest;

import com.company.Summative2MoritaSara.model.Publishers;
import com.company.Summative2MoritaSara.repository.AuthorRepo;
import com.company.Summative2MoritaSara.repository.BooksRepo;
import com.company.Summative2MoritaSara.repository.PublisherRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherRepoTest {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    AuthorRepo authorRepo;
    @Autowired
    PublisherRepo publisherRepo;

    @Before
    public void setUp() throws Exception {
        booksRepo.deleteAll();
        authorRepo.deleteAll();
        publisherRepo.deleteAll();
    }

    @Test
    public void updatePublisher() {
        Publishers publisher = new Publishers();
        publisher.setName("New Publisher");
        publisher.setStreet("Pub st.");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("44444");
        publisher.setPhone("222-555-7777");
        publisher.setEmail("new@publisher.com");

        publisher = publisherRepo.save(publisher);

        publisher.setName("Update Publisher1");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        Optional<Publishers> publisher1 = publisherRepo.findById(publisher.getId());

    }

    @Test
    public void getAllPublishers() {

        Publishers publisher = new Publishers();
        publisher.setName("New Publisher");
        publisher.setStreet("Pub st.");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("44444");
        publisher.setPhone("222-555-7777");
        publisher.setEmail("new@publisher.com");

        Publishers publisher2 = new Publishers();
        publisher2.setName(" The Publishers");
        publisher2.setStreet("Pub 2nd st.");
        publisher2.setCity("New York");
        publisher2.setState("NY");
        publisher2.setPostalCode("44444");
        publisher2.setPhone("111-555-7777");
        publisher2.setEmail("pub2@publisher.com");

        publisher = publisherRepo.save(publisher);
        publisher2 = publisherRepo.save(publisher2);
        List<Publishers> publisherList = publisherRepo.findAll();

        assertTrue(publisherList.contains(publisher));
        assertTrue(publisherList.contains(publisher2));
        assertEquals(2, publisherList.size());


    }









}
