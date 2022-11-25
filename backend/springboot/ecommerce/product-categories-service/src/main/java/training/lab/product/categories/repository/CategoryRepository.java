package training.lab.product.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.lab.product.categories.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
