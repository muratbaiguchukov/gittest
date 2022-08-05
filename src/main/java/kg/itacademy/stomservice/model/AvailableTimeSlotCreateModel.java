package kg.itacademy.stomservice.model;

import kg.itacademy.stomservice.entity.Dentist;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NotNull
public class AvailableTimeSlotCreateModel {

    @NotBlank
    private DentistModel dentist;

    @NotBlank
    private LocalTime startTime;

    @NotBlank
    private LocalTime endTime;

    @NotBlank
    private LocalDate day;
}
