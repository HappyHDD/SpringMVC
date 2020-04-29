package MyMVS.service;

import MyMVS.persist.Product;
import MyMVS.persist.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void save(Product person) {
        productRepository.save(person);
    }

    @Transactional
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

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

    public List<Product> findAllByCostBetween(Optional<Integer> min, Optional<Integer> max) {
        if (min.isPresent() && max.isPresent()) {
            return productRepository.findAllByCostBetween(min.get(), max.get());
        }
        if (min.isPresent()) {
            return productRepository.findAllByCostGreaterThanEqual(min.get());
        }
        if (max.isPresent()) {
            return productRepository.findAllByCostLessThanEqual(max.get());
        }
        return productRepository.findAll();
    }
}
