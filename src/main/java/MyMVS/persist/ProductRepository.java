package MyMVS.persist;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepository {
    private final AtomicInteger identity = new AtomicInteger(0);

    private final List<Product> products = new ArrayList<>();

    public ProductRepository() {
        insert(new Product("Milk", 70));
        insert(new Product("Coffee", 120));
        insert(new Product("Bread", 30));
    }

    public void insert(Product product) {
        product.setId(identity.incrementAndGet());
        products.add(product);
    }

    public List<Product> getAllProduct() {
        return Collections.unmodifiableList(products);
    }
}
