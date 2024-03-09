package com.alphabet.linkedin.reviewms.review;

import java.util.List;

public interface ReviewServiceImpl {


        boolean createReview(Review review, long companyId);
        List<Review> getAllReviews(long companyId);
        Review getReview(long reviewId);
        boolean updateReview(Review updatedReview, long reviewId);
        boolean deleteReview(long reviewId);


}
