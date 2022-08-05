package kg.itacademy.stomservice.model;

import kg.itacademy.stomservice.entity.Card;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class PatientCreateModel {

    @NotBlank(message = "patient last name can't be blank")
    private String patientLastName;

    private String patientFirstName;


    private String patientMidlName;

    @NotBlank
    private String dateOfBirth;

    @NotNull
    private String patientPhoneNumber;

    private Card card;
}
