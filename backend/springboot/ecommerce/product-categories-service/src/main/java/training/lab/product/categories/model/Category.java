package training.lab.product.categories.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @NotBlank(message = "categoryName field Is Mandatory")
    private String categoryName;

    @Length(min = 0, max = 100, message = "description field may be either empty or if present, not exceed 100 characters")
    private String description;

    @Min(value = 0, message = "taxId field must be greater than or equal to zero")
    private Long taxId;
}
