package com.company.Summative2MoritaSara.repositorytest;

import com.company.Summative2MoritaSara.model.Authors;
import com.company.Summative2MoritaSara.repository.AuthorRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorRepoTest {

    @Autowired
    AuthorRepo authorRepo;

    @Before
    public void setUp() throws Exception {
        authorRepo.deleteAll();
    }

    @Test
    public void updateAuthor() {

        Authors author = new Authors();
        author.setFirstName("ABC");
        author.setLastName("de");
        author.setStreet("abc St.");
        author.setCity("Seattle");
        author.setState("WA");
        author.setPostalCode("11111");
        author.setPhone("123-111-1111");
        author.setEmail("abc@de.com");

        author = authorRepo.save(author);

        Optional<Authors> author1 = authorRepo.findById(author.getId());

        author.setStreet("New St.");
        author.setCity("Los Angeles");
        author.setState("CA");
        author.setPostalCode("99999");

        authorRepo.save(author);

        Optional<Authors> author2 = authorRepo.findById(author.getId());

        assertFalse(author1.get().equals(author2));

        assertEquals(author2.get(), author);

        authorRepo.deleteAll();
    }

    @Test
    public void getAllAuthors() {

        Authors author = new Authors();
        author.setFirstName("Coco");
        author.setLastName("Best");
        author.setStreet("Coco St.");
        author.setCity("Santa Ana");
        author.setState("CA");
        author.setPostalCode("99933");
        author.setPhone("123-111-3333");
        author.setEmail("coco@best.com");

        author = authorRepo.save(author);

        Authors author2 = new Authors();
        author2.setFirstName("Oliver");
        author2.setLastName("Best2");
        author2.setStreet("Oliver St.");
        author2.setCity("San Diego");
        author2.setState("CA");
        author2.setPostalCode("33333");
        author2.setPhone("222-333-4444");
        author2.setEmail("oliver@best2.com");

        author2 = authorRepo.save(author2);



        Authors author3 = new Authors();
        author3.setFirstName("Mo");
        author3.setLastName("Best3");
        author3.setStreet("Mo St.");
        author3.setCity("Santa Barbra");
        author3.setState("CA");
        author3.setPostalCode("01010");
        author3.setPhone("111-000-1111");
        author3.setEmail("Mo@best3.com");

        author3 = authorRepo.save(author3);

        List<Authors> authorsList = authorRepo.findAll();
        assertEquals(authorsList.size(), 3);
        assertTrue(authorsList.contains(author2));
        assertTrue(authorsList.contains(author));

        authorRepo.deleteAll();
    }









}
