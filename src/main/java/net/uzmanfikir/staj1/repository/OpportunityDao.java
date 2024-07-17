package net.uzmanfikir.staj1.repository;

import net.uzmanfikir.staj1.persistence.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityDao extends JpaRepository<Opportunity, Long> {
}