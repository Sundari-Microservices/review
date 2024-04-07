package com.alphabet.linkedin.reviewms.review.messaging;

import com.alphabet.linkedin.reviewms.review.Review;
import com.alphabet.linkedin.reviewms.review.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This particular class is going to have all the logic.
 */
@Service
public class ReviewMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * The sendMessage is responsible for producing a message to RabbitMQ Queue.
     */
    public void sendMessage(Review review){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(review.getId());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setCompanyId(review.getCompanyId());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setTitle(review.getTitle());
        rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);
    }
}
