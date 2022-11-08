package com.hanselnpetal.service;

import com.hanselnpetal.domain.CustomerContact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class ContactsManagementServiceIntegrationTest {

    @Autowired
    private ContactsManagementService contactsManagementService;

    @Test
    void testAddContactHappyPath() {

        // Create a contact
        CustomerContact customerContact = new CustomerContact();
        customerContact.setFirstName("James");
        customerContact.setLastName("Bond");

        // Test adding the contact
        CustomerContact newContact = contactsManagementService.add(customerContact);

        // Verify the addition
        assertThat(newContact).isNotNull();
        assertThat(newContact.getFirstName()).isEqualTo("James");
        assertThat(newContact.getLastName()).isEqualTo("Bond");
    }
}
