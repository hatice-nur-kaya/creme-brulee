package net.uzmanfikir.staj1.services;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.CompanyDTO;
import net.uzmanfikir.staj1.persistence.Company;
import net.uzmanfikir.staj1.repository.CompanyDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyDao companyDao;

    public List<Company> getCompanies() {
        return companyDao.findAll();
    }

    public Company create(CompanyDTO company) {
        return companyDao.save(Company.builder()
                .name(company.getName())
                .website(company.getWebsite())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .build());
    }

    public Company update(CompanyDTO companyToEdit) {
        var company = companyDao.findById(companyToEdit.getId()).orElseThrow();
        company.setName(companyToEdit.getName());
        company.setWebsite(companyToEdit.getWebsite());
        company.setAddress(companyToEdit.getAddress());
        company.setPhoneNumber(companyToEdit.getPhoneNumber());
        return companyDao.save(company);
    }

    public void deleteCompany(Long id) {
        companyDao.deleteById(id);
    }

    public Optional<Company> findById(Long id) {
        return companyDao.findById(id);
    }
}