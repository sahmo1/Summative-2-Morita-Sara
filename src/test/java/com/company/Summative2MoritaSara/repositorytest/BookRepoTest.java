package com.company.Summative2MoritaSara.repositorytest;

import com.company.Summative2MoritaSara.model.Authors;
import com.company.Summative2MoritaSara.model.Books;
import com.company.Summative2MoritaSara.model.Publishers;
import com.company.Summative2MoritaSara.repository.AuthorRepo;
import com.company.Summative2MoritaSara.repository.BooksRepo;
import com.company.Summative2MoritaSara.repository.PublisherRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookRepoTest {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    AuthorRepo authorRepo;
    @Autowired
    PublisherRepo publisherRepo;

    public void setUp() throws Exception{
        booksRepo.deleteAll();
        authorRepo.deleteAll();
        publisherRepo.deleteAll();
    }

    @Test
    public void getAllBooks(){
        Authors author = new Authors();

        author.setFirstName("Hello");
        author.setLastName("World");
        author.setStreet("Code St.");
        author.setCity("San Francisco");
        author.setState("CA");
        author.setPostalCode("00000");
        author.setPhone("123-111-1111");
        author.setEmail("hello@world.com");

        author = authorRepo.save(author);

        Publishers publisher = new Publishers();
        publisher.setName("Pub1");
        publisher.setState("NY");
        publisher.setStreet("Pub st.");
        publisher.setPostalCode("22222");
        publisher.setCity("New York");
        publisher.setPhone("333-444-5555");
        publisher.setEmail("pub1@publisher.com");

        publisher = publisherRepo.save(publisher);


        Books book = new Books();
        book.setAuthorID(author.getId());
        book.setPublisherID(publisher.getId());
        book.setTitle("Test Title");
        book.setIsbn("ibsn12345");
        book.setPrice(20);

        book = booksRepo.save(book);

        List<Books> bookList = booksRepo.findAll();
        assertEquals(bookList.size(), 1);

    }

    @Test
    public void getBooksByAuthorId() {
        Authors author = new Authors();
        author.setFirstName("Hello");
        author.setLastName("Kitty");
        author.setStreet("HK St.");
        author.setCity("LA");
        author.setState("CA");
        author.setPostalCode("22222");
        author.setPhone("123-222-3333");
        author.setEmail("hello@kitty.com");

        author = authorRepo.save(author);

        Publishers publisher = new Publishers();
        publisher.setName("Publisher1");

        publisher.setStreet("One St.");

        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("77777");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("publisher1@gmail.com");

        publisher = publisherRepo.save(publisher);

        Books book = new Books();
        book.setAuthorID(author.getId());
        book.setPublisherID(publisher.getId());
        book.setTitle("Title Test");
        book.setIsbn("ibsn12345");
        book.setPrice(15);

        book = booksRepo.save(book);

        Books book2 = new Books();
        book2.setAuthorID(author.getId());
        book2.setPublisherID(publisher.getId());
        book2.setTitle("Test Title 2");
        book2.setIsbn("book2isbn");
        book2.setPrice(25);

        book2 = booksRepo.save(book2);


        Authors author2 = new Authors();
        author2.setFirstName("Second");
        author2.setLastName("Author");
        author2.setStreet("Hello St.");
        author2.setCity("Los Angeles");
        author2.setState("CA");
        author2.setPostalCode("11111");
        author2.setPhone("111-222-6666");
        author2.setEmail("second@pub.com");

        author2 = authorRepo.save(author2);

        Books book3 = new Books();
        book3.setAuthorID(author2.getId());
        book3.setPublisherID(publisher.getId());
        book3.setTitle("Test title3");
        book3.setIsbn("book3isbn");
        book3.setPrice(22);

        book3 = booksRepo.save(book3);

        Integer authorID = author.getId();
        List<Books> bookList = booksRepo.findAllBooksByAuthorID(authorID);

        assertEquals(bookList.size(), 2);

        assertTrue(bookList.contains(book));
        assertTrue(bookList.contains(book2));
    }

    @Test
    public void updateBook() {

        Authors author = new Authors();
        author.setFirstName("Hello");
        author.setLastName("World");
        author.setStreet("World St.");
        author.setCity("Los Angeles");
        author.setState("CA");
        author.setPostalCode("11111");
        author.setPhone("123-433-5555");
        author.setEmail("hello@gmail.com");

        author = authorRepo.save(author);

        Publishers publisher = new Publishers();
        publisher.setName("New Publisher");
        publisher.setStreet("Pub st.");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setPostalCode("44444");
        publisher.setPhone("222-555-7777");
        publisher.setEmail("new@publisher.com");

        publisher = publisherRepo.save(publisher);

        Books book = new Books();
        book.setAuthorID(author.getId());
        book.setPublisherID(publisher.getId());
        book.setTitle("Title Test");
        book.setIsbn("ibsn12345");
        book.setPrice(9);
        book = booksRepo.save(book); //PROBLEM HERE

        Optional<Books> book1 = booksRepo.findById(book.getId());
        book.setTitle("New Title");
        book.setIsbn("newisbn");

        booksRepo.save(book);

        Optional<Books> book2 = booksRepo.findById(book.getId());

        assertEquals(book2.get(), book);

        assertFalse(book1.get().equals(book2));
    }














}
