package com.alphabet.linkedin.reviewms.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements ReviewServiceImpl{

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public boolean createReview(Review review, long companyId){

        if (review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
//        else {
//            // add company and then add review.
////            companyService.createCompany();
//        }
        return false;
    }

    @Override
    public List<Review> getAllReviews(long companyId){
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReview(long reviewId){
         return reviewRepository.findById(reviewId).orElse(null);
    }
//
    @Override
    public boolean updateReview(Review updatedReview, long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setTitle(updatedReview.getTitle());
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }
//
    @Override
    public boolean deleteReview(long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null ){

            reviewRepository.delete(review);
            return true;
        }else{
            return false;
        }
    }
}
