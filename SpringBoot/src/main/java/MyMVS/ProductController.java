package MyMVS;

import MyMVS.persist.Product;
import MyMVS.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public String allProduct(Model model) {
//        model.addAttribute("products", productService.getAllProduct());
//        return "products";
//    }

    @GetMapping("/{id}")
    public String editPerson(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product_form";
    }

    @GetMapping
    public String allProduct(@RequestParam(value = "minPrice") Optional<Integer> minPrice,
                             @RequestParam(value = "maxPrice") Optional<Integer> maxPrice,
                             @RequestParam(value = "page") Optional<Integer> page,
                             @RequestParam(value = "size") Optional<Integer> size,
                             Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("productPage", productService.findAllByCostBetween(
                minPrice, maxPrice,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5))
        ));
        model.addAttribute("minPrice", minPrice.orElse(null));
        model.addAttribute("maxPrice", maxPrice.orElse(null));
        return "products";
    }

    @GetMapping("/form")
    public String formProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/form")
    public String newProduct(Product product) {
        productService.save(product);
        return "redirect:/product";
    }
}
