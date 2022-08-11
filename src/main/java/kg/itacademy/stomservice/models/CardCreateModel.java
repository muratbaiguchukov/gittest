package kg.itacademy.stomservice.models;

import com.sun.istack.NotNull;
import kg.itacademy.stomservice.entity.PatientStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CardCreateModel {

    @NotNull
    Integer number;

    @NotNull
    PatientModel patient;

    @NotNull
    DentistModel dentist;


    DentalWorkModel dentalWork;


    PatientStatus patientStatus;

    BigDecimal debt;
}
