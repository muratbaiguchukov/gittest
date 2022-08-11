package kg.itacademy.stomservice.models;

import kg.itacademy.stomservice.entity.Card;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class PatientModel {

    @NotNull
    private Long id;

    @NotBlank(message = "patient last name can't be blank")
    private String patientLastName;

    private String patientFirstName;


    private String patientMidlName;

    @NotBlank
    private String dateOfBirth;

    @NotNull
    private String patientPhoneNumber;

    //private CardModel card;

}
