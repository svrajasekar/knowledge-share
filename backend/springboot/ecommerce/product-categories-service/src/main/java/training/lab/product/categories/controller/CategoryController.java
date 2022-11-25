package training.lab.product.categories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.lab.product.categories.model.Category;
import training.lab.product.categories.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categoryList = categoryService.getCategories();
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable(value = "id", required = true) Long categoryId) {
        Category result = categoryService.getCategory(categoryId);
        return new ResponseEntity<Category>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category) {
        Category result = categoryService.saveCategory(category);
        return new ResponseEntity<Category>(result, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) {
        Category result = categoryService.updateCategory(category);
        return new ResponseEntity<Category>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id", required = true) Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<String>("Category Record Id: " + categoryId + " Deleted Successfully", HttpStatus.OK);
    }
}
