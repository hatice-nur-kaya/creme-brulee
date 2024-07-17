package net.uzmanfikir.staj1.controllers;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.ContactDTO;
import net.uzmanfikir.staj1.services.ContactService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@Secured("ROLE_USERS")
@RequiredArgsConstructor

public class ContactAPI {
    private final ContactService contactService;

    @GetMapping("/list")
    public List<ContactDTO> listContact() {
        return contactService.getContacts().stream().map(ContactDTO::of).toList();
    }

    @PostMapping("/create")
    public ContactDTO createContact(@RequestBody ContactDTO contact) {
        return ContactDTO.of(contactService.create(contact));
    }

    @PutMapping("/update")
    public ContactDTO updateContact(@RequestBody ContactDTO contact) {
        return ContactDTO.of(contactService.update(contact));
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
    }
}