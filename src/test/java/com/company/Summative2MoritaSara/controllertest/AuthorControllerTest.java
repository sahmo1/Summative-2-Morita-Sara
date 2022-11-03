package com.company.Summative2MoritaSara.controllertest;

import com.company.Summative2MoritaSara.controller.AuthorsController;
import com.company.Summative2MoritaSara.model.Authors;
import com.company.Summative2MoritaSara.repository.AuthorRepo;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorsController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepo authorRepo;

    private ObjectMapper mapper = new ObjectMapper();

    private Authors author;
    private String authorJson;

    private List<Authors> allAuthor = new ArrayList<>();
    private String allAuthorJson;


    @Before
    public void setup() throws Exception {
        author = new Authors();
        author.setId(1);
        author.setFirstName("Eric");
        author.setLastName("Hartman");
        author.setStreet("123 ABC");
        author.setCity("Denver");
        author.setState("CO");
        author.setPostalCode("12345");
        author.setPhone("123-456-7899");
        author.setEmail("eric@cartman.com");

        authorJson = mapper.writeValueAsString(author);

        Authors author2 = new Authors();
        author2.setId(2);
        author2.setFirstName("Sponge");
        author2.setLastName("Bob");
        author2.setStreet("456 ABC");
        author2.setCity("Los Angeles");
        author2.setState("CA");
        author2.setPostalCode("90403");
        author2.setPhone("987-654-3211");
        author2.setEmail("sponge@bob.com");

        allAuthor.add(author);
        allAuthor.add(author2);

        allAuthorJson = mapper.writeValueAsString(allAuthor);

    }
    @Test
    public void shouldCreateNewAuthorOnPostRequest() throws Exception {
        Authors inputAuthor = new Authors();

        inputAuthor.setId(1);
        inputAuthor.setFirstName("Eric");
        inputAuthor.setLastName("Hartman");
        inputAuthor.setStreet("123 ABC");
        inputAuthor.setCity("Denver");
        inputAuthor.setState("CO");
        inputAuthor.setPostalCode("12345");
        inputAuthor.setPhone("123-456-7899");
        inputAuthor.setEmail("eric@cartman.com");

        String inputJson = mapper.writeValueAsString(inputAuthor);

        doReturn(author).when(authorRepo).save(inputAuthor);

        mockMvc.perform(
                        post("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldReturnAuthorById() throws Exception {
        Authors author = new Authors();
        Authors inputAuthor2 = new Authors();
        inputAuthor2.setId(2);
        inputAuthor2.setFirstName("Sponge");
        inputAuthor2.setLastName("Bob");
        inputAuthor2.setStreet("456 ABC");
        inputAuthor2.setCity("Los Angeles");
        inputAuthor2.setState("CA");
        inputAuthor2.setPostalCode("90403");
        inputAuthor2.setPhone("987-654-3211");
        inputAuthor2.setEmail("sponge@bob.com");
        doReturn(Optional.of(author)).when(authorRepo).findById(1);

        mockMvc.perform(get("/authors/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldBStatusOkForNonExistentAuthorId() throws Exception {
        doReturn(Optional.empty()).when(authorRepo).findById(10);

        mockMvc.perform(
                        get("/authors/10"))
                .andExpect(status().isOk()
                );
    }


    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/authors/2")).andExpect(status().isNoContent());
    }



}




