package MyMVS;

import MyMVS.persist.Product;
import MyMVS.persist.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String allPersons(Model model) {
        model.addAttribute("products", productRepository.getAllProduct());
        return "products";
    }

    @GetMapping("/form")
    public String formPerson(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/form")
    public String newProduct(Product product) {
        productRepository.insert(product);
        return "redirect:/product";
    }
}
