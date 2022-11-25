package training.lab.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.dto.manufacturers.Manufacturer;
import training.lab.frontend.service.ManufacturerService;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Manufacturer>> getManufacturers() {
        Flux<Manufacturer> response = manufacturerService.getManufacturers();
        return new ResponseEntity<Flux<Manufacturer>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Manufacturer>> getManufacturer(@PathVariable(value = "id", required = false) Long manufacturerId) {
        Mono<Manufacturer> response = manufacturerService.getManufacturer(manufacturerId);
        return new ResponseEntity<Mono<Manufacturer>>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Manufacturer>> saveManufacturer(@RequestBody Manufacturer manufacturer) {
        Mono<Manufacturer> response = manufacturerService.saveManufacturer(manufacturer);
        return new ResponseEntity<Mono<Manufacturer>>(response, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Manufacturer>> updateManufacturer(@RequestBody Manufacturer manufacturer) {
        Mono<Manufacturer> response = manufacturerService.updateManufacturer(manufacturer);
        return new ResponseEntity<Mono<Manufacturer>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Mono<String>> deleteCategory(@PathVariable(value = "id", required = false) Long manufacturerId) {
        Mono<String> response = manufacturerService.deleteManufacturer(manufacturerId);
        return new ResponseEntity<Mono<String>>(response, HttpStatus.OK);
    }
}
