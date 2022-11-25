package training.lab.product.manufacturers.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long manufacturerId;

    @NotBlank(message = "manufacturerName field Is Mandatory")
    private String manufacturerName;

    @Length(min = 0, max = 200, message = "streetAddress must not exceed 200 characters")
    private String streetAddress;

    @Length(min = 0, max = 50, message = "city must not exceed 50 characters")
    private String city;

    @Length(min = 0, max = 50, message = "state must not exceed 50 characters")
    private String state;

    @Length(min = 0, max = 15, message = "zip must not exceed 15 characters")
    private String zip;

    @Length(min = 0, max = 50, message = "country must not exceed 15 characters")
    private String country;

    @Length(min = 0, max = 50, message = "phoneNumbers must not exceed 50 characters")
    private String phoneNumbers;

    @Length(min = 0, max = 50, message = "facsimileNumbers must not exceed 50 characters")
    private String facsimileNumbers;

    @Length(min = 0, max = 50, message = "emailAddresses must not exceed 50 characters")
    private String emailAddresses;
}
