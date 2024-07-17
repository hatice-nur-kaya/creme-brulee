package net.uzmanfikir.staj1.controllers;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.ProductDTO;
import net.uzmanfikir.staj1.controllers.dto.ProductCategoryDTO;
import net.uzmanfikir.staj1.services.ProductService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Secured("ROLE_USERS")
@RequiredArgsConstructor
public class ProductAPI {
    private final ProductService productService;

    @GetMapping("/list")
    public List<ProductDTO> listProducts() {
        return productService.getProducts().stream().map(ProductDTO::of).toList();
    }

    @GetMapping("/listCategories")
    public List<ProductCategoryDTO> listCategories() {
        return productService.getCategories().stream().map(ProductCategoryDTO::of).toList();
    }

    @PostMapping("/create")
    public ProductDTO createProduct(@RequestBody ProductDTO product) {
        return ProductDTO.of(productService.create(product));
    }

    @PostMapping("/createCategory")
    public ProductCategoryDTO createCategory(@RequestBody ProductCategoryDTO productCategory) {
        return ProductCategoryDTO.of(productService.createProductCategory(productCategory));
    }

    @PutMapping("/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO product) {
        return ProductDTO.of(productService.update(product));
    }

    @PutMapping("/updateCategory")
    public ProductCategoryDTO updateCategory(@RequestBody ProductCategoryDTO productCategory) {
        return ProductCategoryDTO.of(productService.updateProductCategory(productCategory));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
