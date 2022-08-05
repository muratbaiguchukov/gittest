package kg.itacademy.stomservice.model;

import kg.itacademy.stomservice.entity.Dentist;
import kg.itacademy.stomservice.entity.Patient;
import kg.itacademy.stomservice.entity.RecordStatus;
import kg.itacademy.stomservice.entity.RecordType;
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
public class DentistsAppointmentCreateModel {

    @NotEmpty
    private Dentist dentist;

    @NotBlank(message = "visit date can't be blank")
    private LocalDate visitDate;

    @NotBlank(message = "visit start time can't by blank")
    private LocalTime visitStartTime;

    @NotBlank(message = "visit end time can't by blank")
    private LocalTime visitEndTime;

    @NotBlank(message = "patient can't be blank")
    private Patient patient;

    private RecordStatus recordStatus;

    private String symptoms;

    private String dentistsComments;
}
