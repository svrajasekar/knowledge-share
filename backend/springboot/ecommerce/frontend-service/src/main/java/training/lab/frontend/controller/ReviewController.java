package training.lab.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.lab.frontend.dto.reviews.Review;
import training.lab.frontend.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Review>> getReviews() {
        Flux<Review> response = reviewService.getReviews();
        return new ResponseEntity<Flux<Review>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Review>> getReview(@PathVariable(value = "id", required = true) Long reviewId) {
        Mono<Review> response = reviewService.getReview(reviewId);
        return new ResponseEntity<Mono<Review>>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Review>> saveReview(@RequestBody Review review) {
        Mono<Review> response = reviewService.saveReview(review);
        return new ResponseEntity<Mono<Review>>(response, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Review>> updateReview(@RequestBody Review review) {
        Mono<Review> response = reviewService.updateReview(review);
        return new ResponseEntity<Mono<Review>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Mono<String>> deleteReview(@PathVariable(value = "id", required = false) Long reviewId) {
        Mono<String> response = reviewService.deleteReview(reviewId);
        return new ResponseEntity<Mono<String>>(response, HttpStatus.OK);
    }
}
