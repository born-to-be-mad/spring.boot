package com.hanselnpetal.service;

import com.hanselnpetal.data.repos.CustomerContactRepository;
import com.hanselnpetal.domain.ContactImportantOccasion;
import com.hanselnpetal.domain.CustomerContact;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactsManagementService {

	private final CustomerContactRepository customerContactRepository;
	
	public CustomerContact add(CustomerContact contact) {
		CustomerContact newContact = customerContactRepository.save(contact);
		return newContact;
	}
	
	public CustomerContact addContactOccasion(CustomerContact contact, ContactImportantOccasion anOccasion) {
		CustomerContact newContact = null;
		return newContact;
	}
}
