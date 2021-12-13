package com.revature_team3.backend.Web;

import com.revature_team3.backend.Entity.Review;
import com.revature_team3.backend.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Optional;

public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/reviews",
            produces = {"application/json","application/xml"}
    )
    public Collection<Review> getAll() {
        return reviewRepository.findAll();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/phone-shop/reviews/{reviewId}",
            produces = {"application/json","application/xml"}
    )
    public ResponseEntity<?> getById(@PathVariable(name="reviewId") int reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.ok(review.get());
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/phone-shop/reviews",
            consumes = {"application/json", "application/xml"}
    )
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        review = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/phone-shop/reviews/{reviewId}"
    )
    public ResponseEntity<?> updateReview(
            @PathVariable(name = "reviewId") int reviewId,
            @RequestBody Review review
    ) {
        review.setId(reviewId);
        review = reviewRepository.save(review); // update
        return ResponseEntity.ok(review);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/phone-shop/reviews/{reviewId}"
    )
    public ResponseEntity<?> deleteReview(@PathVariable(name="reviewId") int reviewId) {
        reviewRepository.deleteById(reviewId);
        return ResponseEntity.ok().build();
    }

}
