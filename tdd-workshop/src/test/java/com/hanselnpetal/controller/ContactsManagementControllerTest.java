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
@ExtendWith(MockitoExtension.class)
class ContactsManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactsManagementService service;

    @InjectMocks
    private ContactsManagementController controller;

    @BeforeEach
    void setUp() {
        controller = new ContactsManagementController(service);
    }

    @Test
    public void testAddContactHappyPath() throws Exception {
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
    public void testAddContactBizServiceRuleNotSatisfied() throws Exception {
        // setup a mock response of NULL object returned from the mock service component
        Mockito.when(service.add(any(CustomerContact.class)))
                .thenReturn(null);

        // simulate the form bean that would POST from the web page
        CustomerContact aContact = new CustomerContact();
        aContact.setLastName("Johnson");

        // simulate the form submit (POST)
        mockMvc
                .perform(post("/addContact", aContact))
                .andExpect(status().is(302))
                .andReturn();
    }

    @Test
    public void testAddContactOccasionHappyPath() throws Exception {
        // implement this
    }

}