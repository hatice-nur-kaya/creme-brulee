package net.uzmanfikir.staj1.services;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.OpportunityDTO;
import net.uzmanfikir.staj1.persistence.Opportunity;
import net.uzmanfikir.staj1.repository.OpportunityDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpportunityService {
    private final OpportunityDao opportunityDao;
    private final UserService userService;
    private final ContactService contactService;

    public List<Opportunity> getOpportunities() {
        return opportunityDao.findAll();
    }

    public Opportunity create(OpportunityDTO opportunity) {
        return opportunityDao.save(Opportunity.builder()
                .status(opportunity.getStatus())
                .discount(opportunity.getDiscount())
                .unit(opportunity.getUnit())
                .contact(contactService.findById(opportunity.getContactId()).orElseThrow())
                .salesPerson(userService.findById(opportunity.getSalesPersonId()).orElseThrow())
                .invoiceDate(opportunity.getInvoiceDate())
                .build());
    }

    public Opportunity update(OpportunityDTO opportunityToEdit) {
        var opportunity = opportunityDao.findById(opportunityToEdit.getId()).orElseThrow();
        opportunity.setSalesPerson(userService.findById(opportunityToEdit.getSalesPersonId()).orElseThrow());
        opportunity.setStatus(opportunityToEdit.getStatus());
        opportunity.setContact(contactService.findById(opportunityToEdit.getContactId()).orElseThrow());
        opportunity.setUnit(opportunityToEdit.getUnit());
        opportunity.setDiscount(opportunityToEdit.getDiscount());
        opportunity.setInvoiceDate(opportunityToEdit.getInvoiceDate());
        return opportunityDao.save(opportunity);
    }

    public void deleteOpportunity(Long id) {
        opportunityDao.deleteById(id);
    }
}