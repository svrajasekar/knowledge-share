package training.lab.frontend.dto.manufacturers;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {
    private Long manufacturerId;
    private String manufacturerName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNumbers;
    private String facsimileNumbers;
    private String emailAddresses;
}
