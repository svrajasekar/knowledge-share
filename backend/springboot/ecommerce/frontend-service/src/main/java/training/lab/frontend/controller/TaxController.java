package training.lab.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.dto.taxes.Tax;
import training.lab.frontend.service.TaxService;

@RestController
@RequestMapping("/taxes")
public class TaxController {

    @Autowired
    TaxService taxService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Tax>> getTaxes() {
        Flux<Tax> response = taxService.getTaxes();
        return new ResponseEntity<Flux<Tax>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Tax>> getTax(@PathVariable(value = "id", required = false) Long taxId) {
        Mono<Tax> response = taxService.getTax(taxId);
        return new ResponseEntity<Mono<Tax>>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Tax>> saveTax(@RequestBody Tax tax) {
        Mono<Tax> response = taxService.saveTax(tax);
        return new ResponseEntity<Mono<Tax>>(response, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Tax>> updateTax(@RequestBody Tax tax) {
        Mono<Tax> response = taxService.updateTax(tax);
        return new ResponseEntity<Mono<Tax>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Mono<String>> deleteTax(@PathVariable(value = "id", required = false) Long taxId) {
        Mono<String> response = taxService.deleteTax(taxId);
        return new ResponseEntity<Mono<String>>(response, HttpStatus.OK);
    }
}
