package training.lab.product.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.lab.product.reviews.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
