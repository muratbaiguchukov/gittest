package kg.itacademy.stomservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class WorkShiftCreateModel {

    @NotNull(message = "dentist can't be blank")
    DentistModel dentist;

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;

    @NotNull
    LocalTime workShiftStartTime;

    @NotNull
    LocalTime workShiftEndTime;

    @NotNull
    LocalDate workShiftDay;
}






