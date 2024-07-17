package net.uzmanfikir.staj1.repository;

import net.uzmanfikir.staj1.persistence.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends JpaRepository<Company, Long> {
}
