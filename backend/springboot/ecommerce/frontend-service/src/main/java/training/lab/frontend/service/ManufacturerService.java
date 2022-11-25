package training.lab.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.config.FrontendApi;
import training.lab.frontend.dto.manufacturers.Manufacturer;
import training.lab.frontend.exception.ResourceNotFoundException;

@Service
public class ManufacturerService {

    private FrontendApi frontendApi;
    private String apiEndpoint;

    @Autowired
    public ManufacturerService(Environment env, FrontendApi frontendApi) {
        this.frontendApi = frontendApi;
        this.apiEndpoint = env.getProperty("manufacturers.api.url");
    }

    public Flux<Manufacturer> getManufacturers() {
        Flux<Manufacturer> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/manufacturers")
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
        .bodyToFlux(Manufacturer.class);
        return response;
    }

    public Mono<Manufacturer> getManufacturer(Long manufacturerId) {
        Mono<Manufacturer> response = this.frontendApi.getWebClient(this.apiEndpoint).get()
        .uri("/manufacturers/" + manufacturerId)
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
        .bodyToMono(Manufacturer.class);
        return response;
    }

    public Mono<Manufacturer> saveManufacturer(Manufacturer manufacturer) {
        Mono<Manufacturer> response = this.frontendApi.getWebClient(this.apiEndpoint).post()
        .uri("/manufacturers")
        .body(Mono.just(manufacturer), Manufacturer.class)
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
        .bodyToMono(Manufacturer.class);
        return response;
    }

    public Mono<Manufacturer> updateManufacturer(Manufacturer manufacturer) {
        Mono<Manufacturer> response = this.frontendApi.getWebClient(this.apiEndpoint).put()
        .uri("/manufacturers")
        .body(Mono.just(manufacturer), Manufacturer.class)
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
        .bodyToMono(Manufacturer.class);
        return response;
    }

    public Mono<String> deleteManufacturer(Long manufacturerId) {
        Mono<String> response = this.frontendApi.getWebClient(this.apiEndpoint).delete()
        .uri("/manufacturers/" + manufacturerId)
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
