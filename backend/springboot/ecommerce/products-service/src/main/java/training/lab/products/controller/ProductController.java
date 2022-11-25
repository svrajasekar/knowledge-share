package training.lab.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.lab.products.model.Product;
import training.lab.products.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productList = productService.getProducts();
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id", required = true) Long productId) {
        Product result = productService.getProduct(productId);
        return new ResponseEntity<Product>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        Product result = productService.saveProduct(product);
        return new ResponseEntity<Product>(result, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
        Product result = productService.updateProduct(product);
        return new ResponseEntity<Product>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id", required = true) Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<String>("Product Record Id: " + productId + " Deleted Successfully", HttpStatus.OK);
    }
}
