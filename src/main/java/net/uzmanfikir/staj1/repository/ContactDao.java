package net.uzmanfikir.staj1.repository;

import net.uzmanfikir.staj1.persistence.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactDao extends JpaRepository<Contact, Long> {
}
