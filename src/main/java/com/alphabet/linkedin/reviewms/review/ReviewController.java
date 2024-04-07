package com.alphabet.linkedin.reviewms.review;

import com.alphabet.linkedin.reviewms.review.messaging.ReviewMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {


    /**
     * GET /reviews?companyId={companyId}
     * POST /reviews?companyId={companyId}
     * GET /reviews/{reviewId}
     * PUT /reviews/{reviewId}
     * DELETE /reviews/{reviewId}
     */
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewMessageProducer reviewMessageProducer;

    //    Get => /reviews?companyId={companyId}

    @GetMapping("")
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

//    Get => /reviews/{reviewId}
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewByReviewId(@PathVariable long reviewId) {
        Review review = reviewService.getReview(reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Review(), HttpStatus.NOT_FOUND);
        }

    }

    //    post => /reviews?companyId={companyId}
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody Review review, @RequestParam long companyId) {

        boolean status = reviewService.createReview(review, companyId);
        if (status) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("review created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("failed to create review", HttpStatus.NOT_FOUND);
        }

    }

//    Put => /reviews/{reviewId}
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewByReviewId(@RequestBody Review review, @PathVariable long reviewId) {
        boolean status = reviewService.updateReview(review, reviewId);
        if (status) {
            return new ResponseEntity<>("review updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("failed to update review.", HttpStatus.NOT_FOUND);
        }

    }

//    Delete => /reviews/{reviewId}
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewByReviewId(@PathVariable long reviewId) {
        boolean status = reviewService.deleteReview(reviewId);
        if (status) {
            return new ResponseEntity<>("review deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("failed to delete review.", HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/averageRating")
    public Double getAverageReviewRating(@RequestParam("companyId") long comapnyId) {
        List<Review> reviews = reviewService.getAllReviews(comapnyId);
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }



}
