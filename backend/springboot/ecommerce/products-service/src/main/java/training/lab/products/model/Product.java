package training.lab.products.model;

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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotBlank(message = "productName field Is Mandatory")
    @Length(min = 2, max = 100)
    private String productName;

    @Length(min = 0, max = 100, message = "description field may be either empty or if present, not exceed 100 characters")
    private String description;

    @Min(value = 0, message = "price field must be greater than or equal to zero")
    private Double price;

    @Min(value = 0, message = "manufacturerId field must be greater than or equal to zero")
    private Long manufacturerId;

    @Min(value = 0, message = "categoryId field must be greater than or equal to zero")
    private Long categoryId;

    @Min(value = 0, message = "taxId field must be greater than or equal to zero")
    private Long taxId;
}
