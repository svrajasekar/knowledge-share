package training.lab.frontend.dto.taxes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tax {
    private Long taxId;
    private String taxName;
    private String description;
    private Double taxPercentage;
}
