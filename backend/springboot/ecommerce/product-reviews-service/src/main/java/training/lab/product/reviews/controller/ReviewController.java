package training.lab.product.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import training.lab.product.reviews.model.Review;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private training.lab.product.reviews.service.ReviewService ReviewService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Review>> getReviews() {
        List<Review> ReviewList = ReviewService.getReviews();
        return new ResponseEntity<List<Review>>(ReviewList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> getReview(@PathVariable(value = "id", required = true) Long ReviewId) {
        Review result = ReviewService.getReview(ReviewId);
        return new ResponseEntity<Review>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> saveReview(@Valid @RequestBody Review Review) {
        Review result = ReviewService.saveReview(Review);
        return new ResponseEntity<Review>(result, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> updateReview(@Valid @RequestBody Review Review) {
        Review result = ReviewService.updateReview(Review);
        return new ResponseEntity<Review>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "id", required = true) Long reviewId) {
        ReviewService.deleteReview(reviewId);
        return new ResponseEntity<String>("Review Record Id: " + reviewId + " Deleted Successfully", HttpStatus.OK);
    }
}
