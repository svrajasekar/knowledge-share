package training.lab.product.taxes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.lab.product.taxes.model.Tax;
import training.lab.product.taxes.service.TaxService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/taxes")
public class TaxController {
    @Autowired
    private TaxService taxService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tax>> getTaxes() {
        List<Tax> taxList = taxService.getTaxes();
        return new ResponseEntity<List<Tax>>(taxList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tax> getTax(@PathVariable(value = "id", required = true) Long taxId) {
        Tax result = taxService.getTax(taxId);
        return new ResponseEntity<Tax>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tax> saveTax(@Valid @RequestBody Tax tax) {
        Tax result = taxService.saveTax(tax);
        return new ResponseEntity<Tax>(result, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tax> updateTax(@Valid @RequestBody Tax tax) {
        Tax result = taxService.updateTax(tax);
        return new ResponseEntity<Tax>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTax(@PathVariable(value = "id", required = true) Long taxId) {
        taxService.deleteTax(taxId);
        return new ResponseEntity<String>("Tax Record Id: " + taxId + " Deleted Successfully", HttpStatus.OK);
    }
}
