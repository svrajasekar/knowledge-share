package training.lab.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.dto.categories.Category;
import training.lab.frontend.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Category>> getCategories() {
        Flux<Category> response = categoryService.getCategories();
        return new ResponseEntity<Flux<Category>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Category>> getCategory(@PathVariable(value = "id", required = true) Long categoryId) {
        Mono<Category> response = categoryService.getCategory(categoryId);
        return new ResponseEntity<Mono<Category>>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Category>> saveCategory(@RequestBody Category category) {
        Mono<Category> response = categoryService.saveCategory(category);
        return new ResponseEntity<Mono<Category>>(response, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Category>> updateCategory(@RequestBody Category category) {
        Mono<Category> response = categoryService.updateCategory(category);
        return new ResponseEntity<Mono<Category>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<String>> deleteCategory(@PathVariable(value = "id", required = false) Long categoryId) {
        Mono<String> response = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<Mono<String>>(response, HttpStatus.OK);
    }
}
