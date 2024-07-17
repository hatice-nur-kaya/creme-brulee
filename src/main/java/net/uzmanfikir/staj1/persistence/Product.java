package net.uzmanfikir.staj1.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "products")
@SuperBuilder
@NoArgsConstructor
public class Product extends BaseEntity {
    private String name;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

}
