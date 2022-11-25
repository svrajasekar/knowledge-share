package training.lab.product.manufacturers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.lab.product.manufacturers.model.Manufacturer;
import training.lab.product.manufacturers.service.ManufacturerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Manufacturer>> getManufacturers() {
        List<Manufacturer> manufacturerList = manufacturerService.getManufacturers();
        return new ResponseEntity<List<Manufacturer>>(manufacturerList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> getManufacturer(@PathVariable(value = "id", required = true) Long manufacturerId) {
        Manufacturer result = manufacturerService.getManufacturer(manufacturerId);
        return new ResponseEntity<Manufacturer>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> saveManufacturer(@Valid @RequestBody Manufacturer manufacturer) {
        Manufacturer result = manufacturerService.saveManufacturer(manufacturer);
        return new ResponseEntity<Manufacturer>(result, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manufacturer> updateManufacturer(@Valid @RequestBody Manufacturer manufacturer) {
        Manufacturer result = manufacturerService.updateManufacturer(manufacturer);
        return new ResponseEntity<Manufacturer>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteManufacturer(@PathVariable(value = "id", required = true) Long manufacturerId) {
        manufacturerService.deleteManufacturer(manufacturerId);
        return new ResponseEntity<String>("Manufacturer Record Id: " + manufacturerId + " Deleted Successfully", HttpStatus.OK);
    }
}
