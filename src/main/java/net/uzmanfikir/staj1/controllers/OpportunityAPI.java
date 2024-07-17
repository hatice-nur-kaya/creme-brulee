package net.uzmanfikir.staj1.controllers;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.OpportunityDTO;
import net.uzmanfikir.staj1.services.OpportunityService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
@Secured("ROLE_OPPORTUNITIES")
@RequiredArgsConstructor

public class OpportunityAPI {
    private final OpportunityService opportunityService;

    @GetMapping("/list")
    public List<OpportunityDTO> listOpportunities() {
        return opportunityService.getOpportunities().stream().map(OpportunityDTO::of).toList();
    }

    @PostMapping("/create")
    public OpportunityDTO createOpportunity(@RequestBody OpportunityDTO opportunity) {
        return OpportunityDTO.of(opportunityService.create(opportunity));
    }

    @PutMapping("/update")
    public OpportunityDTO updateOpportunity(@RequestBody OpportunityDTO opportunity) {
        return OpportunityDTO.of(opportunityService.update(opportunity));
    }

    @DeleteMapping("/{id}")
    public void deleteOpportunity(@PathVariable("id") Long id) {
        opportunityService.deleteOpportunity(id);
    }
}