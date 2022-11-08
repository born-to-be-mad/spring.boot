package com.hanselnpetal.controller;

import com.hanselnpetal.domain.CustomerContact;
import com.hanselnpetal.service.ContactsManagementService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
@WebMvcTest(ContactsManagementController.class)
class ContactsManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactsManagementService service;

    @Test
    void testAddContactHappyPath() throws Exception {
        // setup mock Contact returned the mock service component
        CustomerContact mockCustomerContact = new CustomerContact();
        mockCustomerContact.setFirstName("James");

        Mockito.when(service.add(any(CustomerContact.class)))
                .thenReturn(mockCustomerContact);

        // simulate the form bean that would POST from the web page
        CustomerContact contact = new CustomerContact();
        contact.setFirstName("James");
        contact.setEmail("james@bond.com");

        mockMvc
                .perform(post("/addContact", contact))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testAddContactWhenServiceRuleNotSatisfied() throws Exception {
        // setup a mock response of NULL object returned from the mock service component
        Mockito.when(service.add(any(CustomerContact.class)))
                .thenReturn(null);

        // simulate the form bean that would POST from the web page
        CustomerContact contact = new CustomerContact();
        contact.setLastName("Bond");

        // simulate the form submit (POST)
        mockMvc
                .perform(post("/addContact", contact))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    void testAddContactOccasionHappyPath() throws Exception {
        // implement this
    }

}