package training.lab.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.config.FrontendApi;
import training.lab.frontend.dto.taxes.Tax;
import training.lab.frontend.exception.ResourceNotFoundException;

@Service
public class TaxService {

    private FrontendApi frontendApi;
    private String apiEndpoint;

    @Autowired
    public TaxService(Environment env, FrontendApi frontendApi) {
        this.frontendApi = frontendApi;
        apiEndpoint = env.getProperty("taxes.api.url");
    }

    public Flux<Tax> getTaxes() {
        Flux<Tax> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/taxes")
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
        .bodyToFlux(Tax.class);
        return response;
    }

    public Mono<Tax> getTax(Long taxId) {
        Mono<Tax> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/taxes/" + taxId)
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
        .bodyToMono(Tax.class);
        return response;
    }

    public Mono<Tax> saveTax(Tax tax) {
        Mono<Tax> response = this.frontendApi.getWebClient(this.apiEndpoint).post()
        .uri("/taxes")
        .body(Mono.just(tax), Tax.class)
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
        .bodyToMono(Tax.class);
        return response;
    }

    public Mono<Tax> updateTax(Tax tax) {
        Mono<Tax> response = this.frontendApi.getWebClient(this.apiEndpoint).put()
        .uri("/taxes")
        .body(Mono.just(tax), Tax.class)
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
        .bodyToMono(Tax.class);
        return response;
    }

    public Mono<String> deleteTax(Long taxId) {
        Mono<String> response = this.frontendApi.getWebClient(this.apiEndpoint).delete()
        .uri("/taxes/" + taxId)
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
