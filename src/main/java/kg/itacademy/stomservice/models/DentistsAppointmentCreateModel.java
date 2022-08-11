package kg.itacademy.stomservice.models;

import kg.itacademy.stomservice.entity.RecordStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class DentistsAppointmentCreateModel {

    @NotNull
    private DentistModel dentist;

    @NotNull(message = "visit date can't be blank")
    private LocalDate visitDate;

    @NotNull(message = "visit start time can't by blank")
    private LocalTime visitStartTime;

    @NotNull(message = "visit end time can't by blank")
    private LocalTime visitEndTime;

    @NotNull(message = "patient can't be blank")
    private PatientModel patient;

    @NotNull(message = "record status can't by blank")
    private RecordStatus recordStatus;

    private String symptoms;

    private String dentistsComments;
}
