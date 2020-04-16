package MyMVS.service;

import MyMVS.persist.Product;
import MyMVS.persist.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository personRepository) {
        this.productRepository = personRepository;
    }

    @Transactional
    public void insert(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void update(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> getSortProduct(int min, int max) {
        return productRepository.filterProduct(min,max);
    }
}
