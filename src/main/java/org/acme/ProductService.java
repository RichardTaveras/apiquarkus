package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<Product> listAll() {
        return productRepository.listAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void create(Product product) {
        productRepository.persist(product);
    }

    @Transactional
    public void update(Long id, Product product) {
        Product entity = productRepository.findById(id);
        if (entity != null) {
            entity.setName(product.getName());
            entity.setPrice(product.getPrice());
            entity.setDescription(product.getDescription());
        }
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
