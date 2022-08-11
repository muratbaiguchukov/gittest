package kg.itacademy.stomservice.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class DentalWorkModel {

    @NotNull
    private Long id;

    @NotBlank
    String dentalWorkName;

    Integer code;

    String category;

    DentistModel dentist;

    BigDecimal price;
}
