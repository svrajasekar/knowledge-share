package training.lab.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.config.FrontendApi;
import training.lab.frontend.dto.reviews.Review;
import training.lab.frontend.exception.ResourceNotFoundException;

@Service
public class ReviewService {

    private FrontendApi frontendApi;
    private String apiEndpoint;

    @Autowired
    public ReviewService(Environment env, FrontendApi frontendApi) {
        this.frontendApi = frontendApi;
        apiEndpoint = env.getProperty("reviews.api.url");
    }

    public Flux<Review> getReviews() {
        Flux<Review> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/reviews")
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
        .bodyToFlux(Review.class);
        return response;
    }

    public Mono<Review> getReview(Long reviewId) {
        Mono<Review> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/reviews/" + reviewId)
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
        .bodyToMono(Review.class);
        return response;
    }

    public Mono<Review> saveReview(Review review) {
        Mono<Review> response = this.frontendApi.getWebClient(this.apiEndpoint).post()
        .uri("/reviews")
        .body(Mono.just(review), Review.class)
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
        .bodyToMono(Review.class);
        return response;
    }

    public Mono<Review> updateReview(Review review) {
        Mono<Review> response = this.frontendApi.getWebClient(this.apiEndpoint).put()
        .uri("/reviews")
        .body(Mono.just(review), Review.class)
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
        .bodyToMono(Review.class);
        return response;
    }

    public Mono<String> deleteReview(Long reviewId) {
        Mono<String> response = this.frontendApi.getWebClient(this.apiEndpoint).delete()
        .uri("/reviews/" + reviewId)
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
