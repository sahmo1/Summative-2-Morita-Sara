package com.company.Summative2MoritaSara.controllertest;


import com.company.Summative2MoritaSara.controller.PublishersController;
import com.company.Summative2MoritaSara.model.Publishers;
import com.company.Summative2MoritaSara.repository.PublisherRepo;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PublishersController.class)
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherRepo pubRepo;

    private ObjectMapper mapper = new ObjectMapper();

    private Publishers publisher;
    private String publisherJson;
    private List<Publishers> allPublisher = new ArrayList<>();
    private String allPublisherJson;


    @Before
    public void setup() throws Exception {
        publisher = new Publishers();
        publisher.setId(1);
        publisher.setName("P1");
        publisher.setState("CA");
        publisher.setStreet("LA St.");
        publisher.setPostalCode("99999");
        publisher.setCity("Santa Monica");
        publisher.setPhone("444-444-4444");
        publisher.setEmail("sm@CA.com");


        publisherJson = mapper.writeValueAsString(publisher);

        Publishers publisher2 = new Publishers();
        publisher2.setName("P2");
        publisher2.setState("CA");
        publisher2.setStreet("Santa Monica St.");
        publisher2.setPostalCode("22222");
        publisher2.setCity("Venice");
        publisher2.setPhone("555-555-5555");
        publisher2.setEmail("p22@venice.com");

        allPublisher.add(publisher);
        allPublisher.add(publisher2);

        allPublisherJson = mapper.writeValueAsString(allPublisher);
    }

    @Test
    public void shouldReturnPublisherById() throws Exception {
        doReturn(Optional.of(publisher)).when(pubRepo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/publishers/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(publisherJson))
                );
    }

    @Test
    public void shouldBStatusOkForNonExistentPublisherId() throws Exception {
        doReturn(Optional.empty()).when(pubRepo).findById(25);

        mockMvc.perform(
                        get("/publishers/10"))
                .andExpect(status().isOk()
                );

    }
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                        put("/publishers")
                                .content(publisherJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }











}
