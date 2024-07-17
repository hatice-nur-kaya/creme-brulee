package net.uzmanfikir.staj1.services;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.ContactDTO;
import net.uzmanfikir.staj1.persistence.Contact;
import net.uzmanfikir.staj1.repository.ContactDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactDao contactDao;
    private final CompanyService companyService;

    public List<Contact> getContacts() {

        return contactDao.findAll();
    }

    public Contact create(ContactDTO contact) {
        return contactDao.save(Contact.builder()
                .name(contact.getName())
                .title(contact.getTitle())
                .pronoun(contact.getPronoun())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .position(contact.getPosition())
                .department(contact.getDepartment())
                .linkedInUrl(contact.getLinkedInUrl())
                .company(companyService.findById(contact.getCompanyId()).orElseThrow())
                .build());
    }

    public Contact update(ContactDTO contactToEdit) {
        var contact = contactDao.findById(contactToEdit.getId()).orElseThrow();
        contact.setName(contactToEdit.getName());
        contact.setTitle(contactToEdit.getTitle());
        contact.setPronoun(contactToEdit.getPronoun());
        contact.setPosition(contactToEdit.getPosition());
        contact.setPhone(contactToEdit.getPhone());
        contact.setEmail(contactToEdit.getEmail());
        contact.setDepartment(contactToEdit.getDepartment());
        contact.setLinkedInUrl(contactToEdit.getLinkedInUrl());
        contact.setCompany(companyService.findById(contactToEdit.getCompanyId()).orElseThrow());
        return contactDao.save(contact);
    }

    public void deleteContact(Long id) {
        contactDao.deleteById(id);
    }

   public Optional<Contact> findById(Long id) {return contactDao.findById(id);}
}