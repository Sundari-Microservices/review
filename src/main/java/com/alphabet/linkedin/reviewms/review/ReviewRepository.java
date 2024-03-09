package com.alphabet.linkedin.reviewms.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

    // It queries db like: select * from review where companyId=?;
    List<Review> findByCompanyId(long companyId);

}
