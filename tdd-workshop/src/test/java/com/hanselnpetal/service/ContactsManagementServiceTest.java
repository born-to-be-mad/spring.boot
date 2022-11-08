package com.hanselnpetal.service;

import java.math.BigDecimal;

import com.hanselnpetal.data.repos.CustomerContactRepository;
import com.hanselnpetal.domain.CustomerContact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
@ExtendWith(MockitoExtension.class)
class ContactsManagementServiceTest {

    @Mock
    private CustomerContactRepository repository;

    @InjectMocks
    private ContactsManagementService service;

    @BeforeEach
    void setUp() {
        service = new ContactsManagementService(repository);
    }

    @Test
    void testAddContactHappyPath() {
        // given:
        CustomerContact originalContact = new CustomerContact();
        originalContact.setFirstName("James");
        originalContact.setLastName("Bond");

        Mockito.when(repository.save(any(CustomerContact.class))).thenReturn(originalContact);

        // when:
        CustomerContact newContact = service.add(new CustomerContact());

        // then:
        assertThat(newContact).isNotNull();
        assertThat(newContact.getFirstName()).isEqualTo("James");
        assertThat(newContact.getLastName()).isEqualTo("Bond");
    }

}