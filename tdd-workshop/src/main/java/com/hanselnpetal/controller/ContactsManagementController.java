package com.hanselnpetal.controller;

import com.hanselnpetal.domain.CustomerContact;
import com.hanselnpetal.service.ContactsManagementService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/addContact")
public class ContactsManagementController {

    private final ContactsManagementService service;

    @PostMapping
    public String processAddContactSubmit(CustomerContact contact) {
        CustomerContact newContact = service.add(contact);

        if (newContact != null) {
            return "success";
        }

        return "redirect:/failure";
    }
}
