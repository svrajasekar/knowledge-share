package training.lab.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.dto.products.Product;
import training.lab.frontend.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private Environment env;

    @Autowired
    ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Product>> getProducts() {
        Flux<Product> response = productService.getProducts();
        return new ResponseEntity<Flux<Product>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Product>> getProduct(@PathVariable(value = "id", required = false) Long productId) {
        Mono<Product> response = productService.getProduct(productId);
        return new ResponseEntity<Mono<Product>>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Product>> saveProduct(@RequestBody Product product) {
        Mono<Product> response = productService.saveProduct(product);
        return new ResponseEntity<Mono<Product>>(response, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Product>> updateProduct(@RequestBody Product product) {
        Mono<Product> response = productService.updateProduct(product);
        return new ResponseEntity<Mono<Product>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Mono<String>> deleteProduct(@PathVariable(value = "id", required = false) Long productId) {
        Mono<String> response = productService.deleteProduct(productId);
        return new ResponseEntity<Mono<String>>(response, HttpStatus.OK);
    }
}
