package training.lab.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.config.FrontendApi;
import training.lab.frontend.dto.categories.Category;
import training.lab.frontend.exception.ResourceNotFoundException;

@Service
public class CategoryService {

    private FrontendApi frontendApi;
    private String apiEndpoint;

    @Autowired
    public CategoryService(Environment env, FrontendApi frontendApi) {
        this.frontendApi = frontendApi;
        this.apiEndpoint = env.getProperty("categories.api.url");
    }

    public Flux<Category> getCategories() {
        Flux<Category> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/categories")
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
        .bodyToFlux(Category.class);
        return response;
    }

    public Mono<Category> getCategory(Long categoryId) {
        Mono<Category> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/categories/" + categoryId)
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
        .bodyToMono(Category.class);
        return response;
    }

    public Mono<Category> saveCategory(Category category) {
        Mono<Category> response = this.frontendApi.getWebClient(this.apiEndpoint).post()
        .uri("/categories")
        .body(Mono.just(category), Category.class)
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
        .bodyToMono(Category.class);
        return response;
    }

    public Mono<Category> updateCategory(Category category) {
        Mono<Category> response = this.frontendApi.getWebClient(this.apiEndpoint).put()
        .uri("/categories")
        .body(Mono.just(category), Category.class)
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
        .bodyToMono(Category.class);
        return response;
    }

    public Mono<String> deleteCategory(Long categoryId) {
        Mono<String> response = this.frontendApi.getWebClient(this.apiEndpoint).delete()
        .uri("/categories/" + categoryId)
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
