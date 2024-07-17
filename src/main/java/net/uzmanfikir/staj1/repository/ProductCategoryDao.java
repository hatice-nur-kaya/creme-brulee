package net.uzmanfikir.staj1.repository;

import net.uzmanfikir.staj1.persistence.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Long> {
}
