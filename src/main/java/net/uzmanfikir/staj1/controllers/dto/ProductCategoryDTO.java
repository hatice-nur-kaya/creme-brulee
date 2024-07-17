package net.uzmanfikir.staj1.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.uzmanfikir.staj1.persistence.ProductCategory;
@Data
@SuperBuilder
@NoArgsConstructor
public class ProductCategoryDTO {
    private Long id;
    private String name;

    public static ProductCategoryDTO of(ProductCategory productCategory){
        return ProductCategoryDTO.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .build();
    }
}
