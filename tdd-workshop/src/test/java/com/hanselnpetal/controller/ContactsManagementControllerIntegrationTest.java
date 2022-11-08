package com.hanselnpetal.controller;

import com.hanselnpetal.domain.CustomerContact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContactsManagementControllerIntegrationTest {
    @Autowired
    ContactsManagementController controller;

    @Test
    void testAddContactHappyPath() {
        CustomerContact contact = new CustomerContact();
        contact.setFirstName("Jenny");
        contact.setLastName("Johnson");

        String outcome = controller.processAddContactSubmit(contact);

        assertThat(outcome).isEqualTo("success");
    }

    @Test
    void testAddContactFirstNameMissing() {
        CustomerContact contact = new CustomerContact();

        String outcome = controller.processAddContactSubmit(contact);

        assertThat(outcome).isEqualTo("redirect:/failure");
    }
}