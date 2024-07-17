package net.uzmanfikir.staj1.services;

import lombok.RequiredArgsConstructor;
import net.uzmanfikir.staj1.controllers.dto.ProductCategoryDTO;
import net.uzmanfikir.staj1.controllers.dto.ProductDTO;
import net.uzmanfikir.staj1.persistence.Product;
import net.uzmanfikir.staj1.persistence.ProductCategory;
import net.uzmanfikir.staj1.repository.ProductCategoryDao;
import net.uzmanfikir.staj1.repository.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;

    public List<Product> getProducts() {
        return productDao.findAll();
    }

    public List<ProductCategory> getCategories() {
        return productCategoryDao.findAll();
    }

    public Product create(ProductDTO product) {
        return productDao.save(Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productCategory(this.findById(product.getCategoryId()).orElseThrow())
                .build());
    }

    public ProductCategory createProductCategory(ProductCategoryDTO productCategory) {
        return productCategoryDao.save(ProductCategory.builder()
                .name(productCategory.getName())
                .build());
    }

    public Product update(ProductDTO productToEdit) {
        var product = productDao.findById(productToEdit.getId()).orElseThrow();
        product.setName(productToEdit.getName());
        product.setPrice(productToEdit.getPrice());
        product.setProductCategory(this.findById(productToEdit.getCategoryId()).orElseThrow());
        return productDao.save(product);
    }

    public ProductCategory updateProductCategory(ProductCategoryDTO productCategoryToEdit) {
        var productCategory = productCategoryDao.findById(productCategoryToEdit.getId()).orElseThrow();
        productCategory.setName(productCategoryToEdit.getName());
        return productCategoryDao.save(productCategory);
    }

    public Optional<ProductCategory> findById(Long id) {
        return productCategoryDao.findById(id);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id);
        productCategoryDao.deleteById(id);
    }
}
