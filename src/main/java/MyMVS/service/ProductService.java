package MyMVS.service;

import MyMVS.persist.Product;
import MyMVS.persist.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Product> findAllByCostBetween(Optional<Integer> min, Optional<Integer> max, Pageable pageable) {
        if (min.isPresent() && max.isPresent()) {
            return productRepository.findAllByCostBetween(min.get(), max.get(), pageable);
        }
        if (min.isPresent()) {
            return productRepository.findAllByCostGreaterThanEqual(min.get(), pageable);
        }
        if (max.isPresent()) {
            return productRepository.findAllByCostLessThanEqual(max.get(), pageable);
        }
        return productRepository.findAll(pageable);
    }
}
