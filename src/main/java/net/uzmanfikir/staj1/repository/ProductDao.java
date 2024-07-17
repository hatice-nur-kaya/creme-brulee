package net.uzmanfikir.staj1.repository;

import net.uzmanfikir.staj1.persistence.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product,Long> {

}
