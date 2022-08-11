package kg.itacademy.stomservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AvailableTimeSlotModel {

    @NotNull
    private Long id;

    @NotNull
    private DentistModel dentist;

    @NotNull
    @Column(unique = true)
    private LocalTime startTime;

    @NotNull
    @Column(unique = true)
    private LocalTime endTime;

    @NotNull
    private LocalDate day;

    public Boolean getIsDeleted() {
        return null;
    }
}
