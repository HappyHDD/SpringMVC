package MyMVS.rest;

import MyMVS.persist.Product;
import MyMVS.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/product")
@RestController
public class ProductResource {
    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public void createProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Id field found in create request");
        }
        productService.save(product);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(path = "/{id}/id", produces = "application/json")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteById(id);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public Product findById(@PathVariable("id") long id) {
        return productService.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping(path = "/between/{min}&{max}", produces = "application/json")
    public List<Product> findBetween(@PathVariable("min") Optional<Integer> min, @PathVariable("max") Optional<Integer> max) {
        return productService.findAllByCostBetween(min, max);
    }


    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException exception) {
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String>  illegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
