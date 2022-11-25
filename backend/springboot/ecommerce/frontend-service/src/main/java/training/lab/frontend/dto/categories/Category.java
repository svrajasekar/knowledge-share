package training.lab.frontend.dto.categories;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long categoryId;
    private String categoryName;
    private String description;
    private Long taxId;
}
