package net.uzmanfikir.staj1.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "categories")
@SuperBuilder
@NoArgsConstructor
public class ProductCategory extends BaseEntity {
    private String name;
}
