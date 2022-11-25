package training.lab.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.config.FrontendApi;
import training.lab.frontend.dto.products.Product;
import training.lab.frontend.exception.ResourceNotFoundException;

@Service
public class ProductService {

    private FrontendApi frontendApi;
    private String apiEndpoint;

    @Autowired
    public ProductService(Environment env, FrontendApi frontendApi) {
        this.frontendApi = frontendApi;
        this.apiEndpoint = env.getProperty("products.api.url");
    }
    
    public Flux<Product> getProducts() {
        Flux<Product> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/products")
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new ResourceNotFoundException(message);
            });
        })
        .onStatus(HttpStatus::is5xxServerError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new RuntimeException(message);
            });
        })
        .bodyToFlux(Product.class);
        return response;
    }

    public Mono<Product> getProduct(Long productId) {
        Mono<Product> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/products/" + productId)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new ResourceNotFoundException(message);
            });
        })
        .onStatus(HttpStatus::is5xxServerError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new RuntimeException(message);
            });
        })
        .bodyToMono(Product.class);
        return response;
    }

    public Mono<Product> saveProduct(Product product) {
        Mono<Product> response = this.frontendApi.getWebClient(this.apiEndpoint).post()
        .uri("/products")
        .body(Mono.just(product), Product.class)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new ResourceNotFoundException(message);
            });
        })
        .onStatus(HttpStatus::is5xxServerError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new RuntimeException(message);
            });
        })
        .bodyToMono(Product.class);
        return response;
    }

    public Mono<Product> updateProduct(Product product) {
        Mono<Product> response = this.frontendApi.getWebClient(this.apiEndpoint).put()
        .uri("/products")
        .body(Mono.just(product), Product.class)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new ResourceNotFoundException(message);
            });
        })
        .onStatus(HttpStatus::is5xxServerError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new RuntimeException(message);
            });
        })
        .bodyToMono(Product.class);
        return response;
    }

    public Mono<String> deleteProduct(Long productId) {
        Mono<String> response = this.frontendApi.getWebClient(this.apiEndpoint).delete()
        .uri("/products/" + productId)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new ResourceNotFoundException(message);
            });
        })
        .onStatus(HttpStatus::is5xxServerError, error -> {
            Mono<String> errorMessage = error.bodyToMono(String.class);
            return errorMessage.flatMap(message -> {
                throw new RuntimeException(message);
            });
        })
        .bodyToMono(String.class);
        return response;
    }
}
