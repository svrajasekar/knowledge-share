package training.lab.frontend.dto.products;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;
    private String productName;
    private String description;
    private Double price;
    private Long manufacturerId;
    private Long categoryId;
    private Long taxId;
}
