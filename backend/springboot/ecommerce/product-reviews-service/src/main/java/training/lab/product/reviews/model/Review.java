package training.lab.product.reviews.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;

    @Min(value = 1, message = "productId is mandatory")
    private Long productId;

    @Length(min = 0, max=512, message = "Restrict the comments to 512 characters")
    private String comments;

    @Range(min = 1, max = 5, message = "Rating can only be between 1 and 5")
    private Long rating;
}
