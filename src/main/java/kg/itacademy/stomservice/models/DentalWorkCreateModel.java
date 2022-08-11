package kg.itacademy.stomservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class DentalWorkCreateModel {

    @NotBlank
    String dentalWorkName;

    Integer code;

    String category;

    DentistModel dentist;

    BigDecimal price;
}
