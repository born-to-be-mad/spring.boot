package com.hanselnpetal.service;

import com.hanselnpetal.data.repos.CustomerContactRepository;
import com.hanselnpetal.domain.ContactImportantOccasion;
import com.hanselnpetal.domain.CustomerContact;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ContactsManagementService {

    private final CustomerContactRepository customerContactRepository;

    public CustomerContact add(CustomerContact contact) {
        if (contact.getFirstName() != null && contact.getLastName() != null) {
            return customerContactRepository.save(contact);
        }
        log.warn("Contact not saved, first name or last name is missing");
        return null;
    }

    public CustomerContact addContactOccasion(CustomerContact contact, ContactImportantOccasion anOccasion) {
        CustomerContact newContact = null;
        return newContact;
    }
}
