package kg.itacademy.stomservice.model;

import kg.itacademy.stomservice.entity.Dentist;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NotNull
public class WorkShiftModel {

    @NotNull
    private Long id;

    @NotEmpty(message = "dentist can't be blank")
    Dentist dentist;

    @NotBlank
    LocalDate starDate;

    @NotBlank
    LocalDate endDate;

    @NotBlank
    LocalTime workShiftStartTime;

    @NotBlank
    LocalTime workShiftEndTime;

    @NotBlank
    LocalDate workShiftDay;

}
