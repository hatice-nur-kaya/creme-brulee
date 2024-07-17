package net.uzmanfikir.staj1.controllers;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.CompanyDTO;
import net.uzmanfikir.staj1.services.CompanyService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@Secured("ROLE_USERS")
@RequiredArgsConstructor
public class CompanyAPI {
    private final CompanyService companyService;

    @GetMapping("/list")
    public List<CompanyDTO> getCompanies() {
        return companyService.getCompanies().stream().map(CompanyDTO::of).toList();

    }

    @PostMapping("/create")
    public CompanyDTO createCompany(@RequestBody CompanyDTO company) {
        return CompanyDTO.of(companyService.create(company));
    }

    @PutMapping("/update")
    public CompanyDTO updateCompany(@RequestBody CompanyDTO company) {
        return CompanyDTO.of(companyService.update(company));
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
    }
}