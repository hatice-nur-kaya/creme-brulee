package net.uzmanfikir.staj1.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.uzmanfikir.staj1.persistence.Product;

@Data
@SuperBuilder
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Long price;
    private Long categoryId;

    public static ProductDTO of(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .categoryId(product.getProductCategory().getId())
                .build();
    }
}
