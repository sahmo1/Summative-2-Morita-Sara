package com.company.Summative2MoritaSara.controllertest;


import com.company.Summative2MoritaSara.controller.BooksController;
import com.company.Summative2MoritaSara.model.Authors;
import com.company.Summative2MoritaSara.model.Books;
import com.company.Summative2MoritaSara.model.Publishers;
import com.company.Summative2MoritaSara.repository.BooksRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksRepo bookRepo;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Books> allBooks = new ArrayList<>();
    private String allBooksJson;
    private Books book;
    private String bookJson;


    @Before
    public void setup() throws Exception {
        Authors author = new Authors();
        author.setFirstName("George");
        author.setLastName("Lee");
        author.setStreet("ABC St.");
        author.setCity("New York");
        author.setState("NY");
        author.setPostalCode("11111");
        author.setPhone("123-000-0000");
        author.setEmail("g@lee.com");


        Publishers publisher = new Publishers();
        publisher.setName("Publisher1");
        publisher.setState("CA");
        publisher.setStreet("Hello Dr");
        publisher.setPostalCode("22222");
        publisher.setCity("New York");
        publisher.setPhone("111-111-1111");
        publisher.setEmail("publisher1@pub.com");


        book = new Books();
        book.setId(1);
        book.setAuthorID(author.getId());
        book.setPublisherID(publisher.getId());
        book.setTitle("TestTitle");
        book.setIsbn("ibsn12345");
        book.setPrice(new BigDecimal("9.99"));

        bookJson = mapper.writeValueAsString(book);

        Books book2 = new Books();
        book2.setAuthorID(author.getId());
        book2.setPublisherID(publisher.getId());
        book2.setTitle("TestTitle2");
        book2.setIsbn("book2isbn");
        book2.setPrice(new BigDecimal("10.99"));

        allBooksJson = mapper.writeValueAsString(allBooks);
    }

    @Test
    public void shouldCreateNewBookOnPostRequest() throws Exception {
        Books inputRsvp = new Books();
        inputRsvp.setId(1);
        inputRsvp.setAuthorID(1);
        inputRsvp.setPublisherID(2);
        inputRsvp.setTitle("TestTitle2");
        inputRsvp.setIsbn("book2isbn");
        inputRsvp.setPrice(new BigDecimal("10.99"));
        String inputJson = mapper.writeValueAsString(inputRsvp);

        doReturn(book).when(bookRepo).save(inputRsvp);

        mockMvc.perform(
                        post("/books")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(bookJson));
    }

    @Test
    public void shouldReturnBookById() throws Exception {
        doReturn(Optional.of(book)).when(bookRepo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/books/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(bookJson))
                );
    }

    @Test
    public void shouldReturnAllBooks() throws Exception {
        doReturn(allBooks).when(bookRepo).findAll();

        mockMvc.perform(
                        get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(allBooksJson)
                );
    }

    @Test
    public void shouldReturnAllBooksByAuthorId() throws Exception {
        Books inputBook = new Books();
        inputBook.setId(1);
        inputBook.setAuthorID(1);
        inputBook.setPublisherID(2);
        inputBook.setTitle("TestTitle2");
        inputBook.setIsbn("book2isbn");
        inputBook.setPrice(new BigDecimal("10.99"));
        String inputJson = mapper.writeValueAsString(inputBook);

        doReturn(inputBook).when(bookRepo).save(inputBook);

        bookJson = mapper.writeValueAsString(inputBook);

        mockMvc.perform(
                        post("/books")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(bookJson));

        mockMvc.perform(
                        get("/books/author/1")
                                .content(bookJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldBStatusOkForNonExistentRsvpId() throws Exception {
        doReturn(Optional.empty()).when(bookRepo).findById(35);

        mockMvc.perform(
                        get("/books/10"))
                .andExpect(status().isOk()
                );

    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/books/1")).andExpect(status().isNoContent());
    }













}
